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
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : SocketChannelIn.java
 */
public class SocketChannelIn
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 23333));
//            serverSocketChannel.configureBlocking(true);

            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = socketChannel.read(byteBuffer);
            byteBuffer.flip();
            CharBuffer decode = Charset.forName("UTF-8").decode(byteBuffer);
            System.out.println(new StringBuilder(decode).toString());

            String s = "[server]:" + System.currentTimeMillis();
            socketChannel.write(ByteBuffer.wrap(s.getBytes("UTF-8")));
            socketChannel.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
