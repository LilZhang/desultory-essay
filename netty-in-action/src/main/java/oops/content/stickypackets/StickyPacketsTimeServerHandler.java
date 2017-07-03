package oops.content.stickypackets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class StickyPacketsTimeServerHandler extends ChannelInboundHandlerAdapter
{
    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        body = body.substring(0, req.length - System.lineSeparator().length());
        System.out.println("receive order: " + body +
                "; the counter is: " + (++counter));

        System.out.println("server receive: " + body);
        String current = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date().toString() : "BAD ORDER";
        current = current + System.lineSeparator();
        ByteBuf resp = Unpooled.copiedBuffer(current.getBytes());
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }
}
