package oops.content.httpDemo.handlerForTest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import oops.content.httpDemo.utils.Printer;

public class InboundHandler3 extends ChannelInboundHandlerAdapter
{
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception
    {
        Printer.print("InboundHandler3", "userEventTriggered", ctx, evt);

    }
}
