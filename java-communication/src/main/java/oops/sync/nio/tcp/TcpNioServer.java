/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sync.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-06
 * Project        : desultory-essay
 * File Name      : TempServer.java
 */
public class TcpNioServer
{
    public static void main(String[] args) throws IOException
    {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true)
        {
            // int nKeys = selector.select();
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey key : selectionKeys)
            {
                if (key.isAcceptable())
                {
                    System.out.println("Acceptable");

                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    if (sc == null)
                    {
                        continue;
                    }
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }

                if (key.isConnectable())
                {
                    System.out.println("Connectable");
                }
                else if (key.isReadable())
                {
                    System.out.println("Readable");
                    SocketChannel sc = (SocketChannel) key.channel();
                    StringBuilder sb = new StringBuilder();
                    while (sc.read(buffer) > 0)
                    {
                        buffer.flip();
                        CharBuffer decoded = Charset.defaultCharset().decode(buffer);
                        sb.append(decoded);
                        buffer.clear();
                    }

                    System.out.println("[get]: " + sb.toString());
                    sc.register(selector, SelectionKey.OP_WRITE);
                }
                else if (key.isWritable())
                {
                    System.out.println("Writable");
                    key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.write(ByteBuffer.wrap("reply from server.".getBytes()));
                    sc.close();
                }
            }
        }
    }
}
