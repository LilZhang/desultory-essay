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
 * File Name      : Headquarter.java
 */
public class Headquarter
{
    public static void main(String[] args) throws IOException
    {
        InetAddress address = InetAddress.getByName("224.1.1.1");
        MulticastSocket multicastSocket = new MulticastSocket(8866);

        byte[] bytes = "this is a test message".getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 8899);
        multicastSocket.send(packet);

        multicastSocket.close();
    }

}
