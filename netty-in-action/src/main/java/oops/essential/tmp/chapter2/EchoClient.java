package oops.essential.tmp.chapter2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient
{
    private final String host;

    private final int port;

    public EchoClient(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception
    {
        EventLoopGroup group = new NioEventLoopGroup();
        try
        {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>()
                    {
                        protected void initChannel(SocketChannel ch) throws Exception
                        {
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture future = bootstrap.connect().sync();
            future.channel().closeFuture().sync();
        }
        finally
        {
            group.shutdownGracefully();
        }
    }

    // localhost 9999
    public static void main(String[] args) throws Exception
    {
        if (args.length != 2)
        {
            System.out.printf("Usage: %s <host> <port>\n", EchoClient.class.getSimpleName());
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }

    /*public static void main(String[] args)
    {
        int hash1 = ((int) (732L % 64));

        int hash2 = ((int) (732L & ((1 << 6) - 1))); // h & (length-1)

        System.out.println("hash1: " + hash1);
        System.out.println("hash2: " + hash2);

        System.out.println("" + (1 << 6));
    }*/
}
