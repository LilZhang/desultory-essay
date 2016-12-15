/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-10
 * Project        : desultory-essay
 * File Name      : App.java
 */
public class App
{
    public static void main(String[] args)
    {
        try
        {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 23333));


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}