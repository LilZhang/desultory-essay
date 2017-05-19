/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sync.nio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-06
 * Project        : desultory-essay
 * File Name      : TempClient.java
 */
public class TcpNioClient
{
    public static void main(String[] args) throws IOException
    {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        outter: while (true)
        {
            int nKeys = selector.select();
            if (nKeys > 0)
            {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key : selectionKeys)
                {
                    if (key.isConnectable())
                    {
                        System.out.println("Connectable");
                        SocketChannel sc = (SocketChannel) key.channel();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_WRITE);
                        sc.finishConnect();
                    }
                    else if (key.isAcceptable())
                    {
                        System.out.println("Acceptable");
                    }
                    else if (key.isReadable())
                    {
                        System.out.println("Readable");
                        key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        StringBuilder sb = new StringBuilder();
                        while (sc.read(buffer) > 0)
                        {
                            buffer.flip();
                            CharBuffer decoded = Charset.defaultCharset().decode(buffer);
                            sb.append(decoded);
                            buffer.clear();
                        }
                        System.out.println("[get]: " + sb.toString());
                        break outter;
                    }
                    else if (key.isWritable())
                    {
                        System.out.println("Writable");
                        key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                        SocketChannel sc = (SocketChannel) key.channel();
                        sc.write(ByteBuffer.wrap("info from client.".getBytes()));
                        sc.register(selector, SelectionKey.OP_READ);
                    }
                }
            }
        }

        socketChannel.close();
    }
}
