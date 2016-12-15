/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : ClientDemo.java
 */
public class ClientDemo
{
    public static void main(String[] args)
    {
        try
        {
            Socket socket = new Socket("127.0.0.1", 23333);
            OutputStream out = socket.getOutputStream();
            String s = "<client>: " + System.currentTimeMillis() + "\n";
            System.out.println(s);
            out.write(s.getBytes("UTF-8"));
            out.flush();
            out.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
