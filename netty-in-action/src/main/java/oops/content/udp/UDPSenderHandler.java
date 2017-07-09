package oops.content.udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

public class UDPSenderHandler extends SimpleChannelInboundHandler<DatagramPacket>
{
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception
    {
        String resp = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println("back resp: " + resp);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }
}
