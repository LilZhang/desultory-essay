package oops.content.customProtocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        NettyMessage message = (NettyMessage) msg;

        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HANDSHAKE_RESP.getType())
        {
            if (MessageContentType.BYTE.getType() == message.getHeader().getContentType()
                    && message.getContent()[0] == ((byte) 1))
            {
                System.out.println("login ok: " + message);
                ctx.fireChannelRead(msg);
            }
            else
            {
                System.out.println("login failed, already login yet: " + message);
                ctx.close(); // 握手失败
            }
        }
        else
        {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        ctx.fireExceptionCaught(cause);
    }

    private NettyMessage buildLoginReq()
    {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HANDSHAKE_REQ);
        message.setHeader(header);
        return message;
    }
}
