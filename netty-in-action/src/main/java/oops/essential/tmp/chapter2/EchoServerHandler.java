package oops.essential.tmp.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "channelRead()", ctx);

        ByteBuf in = (ByteBuf) msg;
        System.out.printf("Server received: %s\n",
                in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "channelReadComplete()", ctx);

        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "exceptionCaught()", ctx);

        cause.printStackTrace();
        ctx.close();
    }


    // ====


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "channelRegistered()", ctx);
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "channelUnregistered()", ctx);
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "channelActive()", ctx);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "channelInactive()", ctx);
        super.channelInactive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "userEventTriggered()", ctx);
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "channelWritabilityChanged()", ctx);
        super.channelWritabilityChanged(ctx);
    }

    @Override
    protected void ensureNotSharable()
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "ensureNotSharable()", "no param");
        super.ensureNotSharable();
    }

    @Override
    public boolean isSharable()
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "isSharable()", "no param");
        return super.isSharable();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "handlerAdded()", ctx);
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoServerHandler", "handlerRemoved()", ctx);
        super.handlerRemoved(ctx);
    }
}
