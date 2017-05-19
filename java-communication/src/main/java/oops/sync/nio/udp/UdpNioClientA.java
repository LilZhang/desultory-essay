/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sync.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-08
 * Project        : desultory-essay
 * File Name      : UdpNioClientA.java
 */
public class UdpNioClientA
{
    public static void main(String[] args) throws IOException
    {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress(8866));

        // 这个.connect() 可以将目标指向一台主机，固定的从/向该主机发送或者接收UDP包
        // 造成连接的假象

        /*SocketAddress target = new InetSocketAddress("127.0.0.1", 8899);
        datagramChannel.connect(target);
        datagramChannel.write(ByteBuffer.wrap("message from client".getBytes()));*/

        Selector selector = Selector.open();
        datagramChannel.register(selector, SelectionKey.OP_WRITE);

        selector.select();

        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        for (SelectionKey key : selectionKeys)
        {
            if (key.isWritable())
            {
                key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                DatagramChannel dc = (DatagramChannel) key.channel();
                dc.register(selector, SelectionKey.OP_READ);
                ByteBuffer buffer = ByteBuffer.wrap("text message".getBytes());
                dc.send(buffer, new InetSocketAddress("127.0.0.1", 8899));
            }
        }

        datagramChannel.close();
    }
}
