package oops.essential.bak.chapter4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WritingToAChannel
{
    public void write(Channel channel)
    {
        ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
        ChannelFuture future = channel.writeAndFlush(buf);

        future.addListener(new ChannelFutureListener()
        {
            public void operationComplete(ChannelFuture future) throws Exception
            {
                if (future.isSuccess())
                {
                    System.out.println("write successful");
                }
                else
                {
                    System.out.println("write error");
                }
            }
        });
    }

    // Channel 是线程安全的, 可被使用于多线程环境中
    public void concurrentWrite(final Channel channel)
    {
        final ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8).retain();

        ExecutorService executorService = Executors.newCachedThreadPool();

        // 对 Channel 的操作线程安全
        executorService.execute(() -> channel.writeAndFlush(buf.duplicate()));
        executorService.execute(() -> channel.writeAndFlush(buf.duplicate()));
        executorService.execute(() -> channel.writeAndFlush(buf.duplicate()));
    }
}
