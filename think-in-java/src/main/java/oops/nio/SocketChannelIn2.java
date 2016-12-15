/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : Socket.java
 */
public class SocketChannelIn2
{
    public static void main(String[] args)
    {
        try
        {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 23333));
            serverSocketChannel.configureBlocking(false);

            SelectionKey register = serverSocketChannel.register(selector, SelectionKey.OP_READ);

            while (selector.select() != 0)
            {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext())
                {
                    SelectionKey key = iterator.next();

                    if (key.isConnectable())
                    {
                        System.out.println("isConnectable");
                    }

                    if (key.isAcceptable())
                    {
                        System.out.println("isAcceptable");
                    }

                    if (key.isReadable())
                    {
                        System.out.println("isReadable");
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int read = channel.read(buffer);
                        buffer.flip();
                        CharBuffer decode = Charset.forName("UTF-8").decode(buffer);
                        System.out.println(new StringBuilder(decode).toString());
                    }

                    if (key.isWritable())
                    {
                        System.out.println("isWritable");
                        SocketChannel channel = (SocketChannel) key.channel();
                        String s = "[server]:" + System.currentTimeMillis();
                        channel.write(ByteBuffer.wrap(s.getBytes("UTF-8")));
                        channel.close();
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
