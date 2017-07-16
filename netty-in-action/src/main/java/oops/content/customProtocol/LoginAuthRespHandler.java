package oops.content.customProtocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter
{
    // FIXME: 17-7-16 singleton
    private Map<String, Boolean> nodeCheck = NodeCheck.getInstance().getMap();

    private Set<String> whiteList =
            new HashSet<>(Arrays.asList("127.0.0.1", "192.168.1.1"));

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HANDSHAKE_REQ.getType())
        {
            String nodeId = ctx.channel().remoteAddress().toString();
            NettyMessage loginResp;
            // 重复登录, 不予握手
            if (nodeCheck.containsKey(nodeId))
            {
                System.out.println("[node id conflict]" + nodeId);
                loginResp = loginResp(-1);
            }
            else
            {
                InetSocketAddress address =
                        (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean ok = whiteList.stream()
                        .anyMatch((whiteIp) -> whiteIp.equals(ip));
                if (ok)
                {
                    loginResp = loginResp(1);
                    System.out.println("[node id accepted]" + nodeId);
                    nodeCheck.put(nodeId, true);
                }
                else
                {
                    loginResp = loginResp(-1);
                }
            }

            System.out.printf("login response: %s\n",
                    loginResp.toString());
            ctx.writeAndFlush(loginResp);
        }
        else
        {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        String nodeId = ctx.channel().remoteAddress().toString();
        System.out.println("[no heartbeat, remove it]");
        nodeCheck.remove(nodeId);
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }

    private NettyMessage loginResp(int i)
    {
        NettyMessage loginResp = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HANDSHAKE_RESP);
        header.setContentType(MessageContentType.BYTE);
        loginResp.setHeader(header);
        loginResp.setContent(new byte[]{((byte) i)});
        return loginResp;
    }
}
