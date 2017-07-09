package oops.content.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class UDPReceiverHandler extends SimpleChannelInboundHandler<DatagramPacket>
{
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception
    {
        String req = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println("receiver: " + req);

//        ByteBuf buf = Unpooled.copiedBuffer("[echo]" + req, CharsetUtil.UTF_8);
//        ctx.writeAndFlush(new DatagramPacket(buf, packet.sender()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        ctx.close();
        cause.printStackTrace();
    }
}
