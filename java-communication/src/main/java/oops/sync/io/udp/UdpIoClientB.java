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
 * File Name      : UdpIoClientB.java
 */
public class UdpIoClientB
{
    public static void main(String[] args) throws IOException
    {
        DatagramSocket socket = new DatagramSocket(8899);

        while (true)
        {
            // 接收
            // 127.0.0.1:8899 <- 127.0.0.1:8866
            byte[] buffer = new byte[65507];
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(receivePacket);   // block
            String receiveContent = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("[get]: " + receiveContent);

            // 发送
            // 127.0.0.1:8899 -> 127.0.0.1:8866
            byte[] bytes = (receiveContent + " i get").getBytes();
            socket.send(new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8866));
        }
    }
}
