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
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : SocketChannelOut.java
 */
public class SocketChannelOut
{
    public static void main(String[] args)
    {
        try
        {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 23333));

            String s = "<client>: " + System.currentTimeMillis();
            socketChannel.write(ByteBuffer.wrap(s.getBytes("UTF-8")));

            ByteBuffer allocate = ByteBuffer.allocate(1024);
            socketChannel.read(allocate);
            allocate.flip();
            CharBuffer decode = Charset.forName("UTF-8").decode(allocate);
            System.out.println(new StringBuilder(decode).toString());
            socketChannel.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
