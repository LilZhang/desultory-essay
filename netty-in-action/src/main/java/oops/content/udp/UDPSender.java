package oops.content.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class UDPSender
{
    public void send(String host, int port) throws Exception
    {
        EventLoopGroup group = new NioEventLoopGroup();
        try
        {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new UDPSenderHandler())
            ;

            Channel channel = bootstrap.bind(0).sync().channel();
            ByteBuf buf = Unpooled.copiedBuffer("test String", CharsetUtil.UTF_8);
            DatagramPacket packet = new DatagramPacket(buf, new InetSocketAddress(host, port));
            channel.writeAndFlush(packet);
//            channel.writeAndFlush(packet).sync();

//            if (!channel.closeFuture().await(15000))
//            {
//                System.out.println("超时!");
//            }
        }
        finally
        {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception
    {
        new UDPSender().send("127.0.0.1", 8585);
    }
}
