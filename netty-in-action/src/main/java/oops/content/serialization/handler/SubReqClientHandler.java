package oops.content.serialization.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import oops.content.serialization.model.SubscribeReq;

public class SubReqClientHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        for (int i = 0; i < 10; i++)
        {
            SubscribeReq req = new SubscribeReq();
            req.setAddress("the address");
            req.setPhoneNumber("13800138000");
            req.setSubReqID(i);
            req.setUserName("test");

            ctx.write(req);
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        System.out.printf("Receive server response: [%s]\n", msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }
}
