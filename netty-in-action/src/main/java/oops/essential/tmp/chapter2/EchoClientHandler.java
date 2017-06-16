package oops.essential.tmp.chapter2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "channelActive()", ctx);
        
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",
                CharsetUtil.UTF_8));
    }

    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "channelRead0()", ctx);
        
        System.out.printf("Client received: %s\n", in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "exceptionCaught()", ctx);
        cause.printStackTrace();
        ctx.close();
    }

    // ==

    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "acceptInboundMessage()", "no param");
        return super.acceptInboundMessage(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "channelRead()", ctx);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "channelRegistered()", ctx);
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "channelUnregistered()", ctx);
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "channelInactive()", ctx);
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "channelReadComplete()", ctx);
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "userEventTriggered()", ctx);
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "channelWritabilityChanged()", ctx);
        super.channelWritabilityChanged(ctx);
    }

    @Override
    protected void ensureNotSharable()
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "ensureNotSharable()", "no param");
        super.ensureNotSharable();
    }

    @Override
    public boolean isSharable()
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "isSharable()", "no param");
        return super.isSharable();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "handlerAdded()", ctx);
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception
    {
        System.out.printf(LogFormat.HANDLER_FORMAT, "EchoClientHandler", "handlerRemoved()", ctx);
        super.handlerRemoved(ctx);
    }
}
