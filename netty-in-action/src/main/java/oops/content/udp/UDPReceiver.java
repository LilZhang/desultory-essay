package oops.content.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UDPReceiver
{
    private int port;

    public UDPReceiver(int port)
    {
        this.port = port;
    }

    public void startup() throws Exception
    {
        EventLoopGroup group = new NioEventLoopGroup();
        try
        {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new UDPReceiverHandler());

            bootstrap.bind(port)
                    .sync()
                    .channel()
                    .closeFuture()
                    .await();
        }
        finally
        {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception
    {
        new UDPReceiver(8585).startup();
    }
}
