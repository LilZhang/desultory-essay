/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sync.io.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-09
 * Project        : desultory-essay
 * File Name      : Client.java
 */
public class Client
{
    public static void main(String[] args) throws IOException
    {
        InetAddress address = InetAddress.getByName("224.1.1.1");
        MulticastSocket multicastSocket = new MulticastSocket(8899);
        multicastSocket.joinGroup(address);

        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        multicastSocket.receive(packet);
        String s = new String(packet.getData(), 0, packet.getLength());
        System.out.println(s);

        multicastSocket.close();
    }
}
