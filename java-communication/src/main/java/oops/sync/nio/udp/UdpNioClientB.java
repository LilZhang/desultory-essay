/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sync.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-08
 * Project        : desultory-essay
 * File Name      : UdpNioClientB.java
 */
public class UdpNioClientB
{
    public static void main(String[] args) throws IOException
    {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);

        // for jdk 1.7
        datagramChannel.bind(new InetSocketAddress(8899));

        // for jdk 1.6
        /*DatagramSocket socket = datagramChannel.socket();
        socket.bind(new InetSocketAddress(8899));*/

        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true)
        {
            selector.select();  // block here
            Set<SelectionKey> keys = selector.selectedKeys();
            for (SelectionKey key : keys)
            {
                if (key.isReadable())
                {
                    DatagramChannel dc = (DatagramChannel) key.channel();

                    buffer.clear();
                    SocketAddress address = dc.receive(buffer);
                    buffer.flip();
                    CharBuffer decode = Charset.defaultCharset().decode(buffer);
                    StringBuilder sb = new StringBuilder();
                    sb.append("[get]: ");
                    sb.append(decode);
                    System.out.println(sb.toString());
                }
            }
        }
    }
}
