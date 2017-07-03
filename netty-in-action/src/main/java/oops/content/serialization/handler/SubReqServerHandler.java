package oops.content.serialization.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import oops.content.serialization.model.SubscribeReq;
import oops.content.serialization.model.SubscribeResp;

public class SubReqServerHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        SubscribeReq req = (SubscribeReq) msg;
        if ("test".equalsIgnoreCase(req.getUserName()))
        {
            System.out.printf("Service accept client subscribe req: [%s]\n", req.toString());

            SubscribeResp resp = new SubscribeResp();
            resp.setSubReqID(req.getSubReqID());
            resp.setRespCode(0);
            resp.setDesc("Netty book order succeed.");

            ctx.writeAndFlush(resp);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }
}
