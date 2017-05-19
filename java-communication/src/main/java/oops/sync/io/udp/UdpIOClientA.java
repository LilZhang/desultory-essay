/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sync.io.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-07
 * Project        : desultory-essay
 * File Name      : UdpIOClientA.java
 */
public class UdpIOClientA
{
    public static void main(String[] args) throws IOException
    {
        DatagramSocket socket = new DatagramSocket(8866);


        // 发送
        // 127.0.0.1:8866 -> 127.0.0.1:8899
        byte[] bytes = "this message is for udp test.".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8899);
        socket.send(sendPacket);    // block


        // 接收
        // 127.0.0.1:8866 <- 127.0.0.1:8899
        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
        socket.receive(receivePacket);  // block
        System.out.println("[get]: " + new String(receivePacket.getData()));

        socket.close();
    }
}
