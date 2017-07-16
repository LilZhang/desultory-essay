package oops.content.customProtocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NettyClient
{
    private ScheduledExecutorService executor =
            Executors.newScheduledThreadPool(1);

    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(String host, int port) throws Exception
    {
        try
        {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>()
                    {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception
                        {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyMessageDecoder());
                            pipeline.addLast(new NettyMessageEncoder());
                            pipeline.addLast(new ReadTimeoutHandler(50));
                            pipeline.addLast(new LoginAuthReqHandler());
                            pipeline.addLast(new HeartBeatReqHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port)).sync();
            future.channel().closeFuture().sync();
        }
        finally
        {
            executor.execute(() ->
            {
                try
                {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.printf("[re-connect]: %s:%d\n", host, port);
                    try
                    {
                        connect(host, port);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws Exception
    {
        new NettyClient().connect("127.0.0.1", 8585);
    }
}
