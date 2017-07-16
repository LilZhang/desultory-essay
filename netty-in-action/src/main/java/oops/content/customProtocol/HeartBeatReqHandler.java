package oops.content.customProtocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter
{
    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        NettyMessage message = (NettyMessage) msg;
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HANDSHAKE_RESP.getType())
        {
            heartBeat = ctx.executor().scheduleAtFixedRate(
                    new HeartBeatTask(ctx), 0, 5000, TimeUnit.MILLISECONDS);
        }
        else if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.getType())
        {
            System.out.println("[heartbeat response received]" + message);
        }
        else
        {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        if (heartBeat != null)
        {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }

    private static class HeartBeatTask implements Runnable
    {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(ChannelHandlerContext ctx)
        {
            this.ctx = ctx;
        }

        @Override
        public void run()
        {
            NettyMessage heartBeat = heartBeat();
            System.out.println("[send heartbeat]" + heartBeat);
            ctx.writeAndFlush(heartBeat);
        }

        private NettyMessage heartBeat()
        {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ);
            message.setHeader(header);
            return message;
        }
    }
}
