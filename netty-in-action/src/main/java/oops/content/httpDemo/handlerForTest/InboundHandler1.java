package oops.content.httpDemo.handlerForTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import oops.content.httpDemo.utils.Printer;

public class InboundHandler1 extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        Printer.print("InboundHandler1", "channelRead", ctx, msg);
        FullHttpRequest request = (FullHttpRequest) msg;

        // TODO: 2017/7/6 do something here
        ctx.fireUserEventTriggered("fire 1st");
        System.out.println("sync ?");

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        ByteBuf buffer = Unpooled.copiedBuffer("error!!", CharsetUtil.UTF_8);
        response.content().writeBytes(buffer);
        buffer.release();

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        Printer.print("InboundHandler1", "channelReadComplete", ctx);
        ctx.flush();
    }
}
