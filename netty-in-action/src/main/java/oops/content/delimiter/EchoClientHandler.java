package oops.content.delimiter;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter
{
    private static final String ECHO_REQ = "Hello World NETTY.$_";

    private int counter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        for (int i = 0; i < 10; i++)
        {
            ctx.writeAndFlush(
                    Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        String resp = (String) msg;
        System.out.printf("This is %d times receive server: [%s]\n", ++counter, resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        ctx.flush();

        /*if (counter == 10)
        {
            ctx.close();
        }*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }
}
