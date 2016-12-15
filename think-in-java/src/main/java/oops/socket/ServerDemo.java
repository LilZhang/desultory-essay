/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : ServerDemo.java
 */
public class ServerDemo
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(23333);
            Flag flag = Flag.getInstance();
            flag.setRun(true);
            while (flag.isRun())
            {
                try
                {
                    Socket socket = null;
                    BufferedReader reader = null;

                    try
                    {
                        socket = serverSocket.accept();
                        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

                        String content = reader.readLine();
                        System.out.println(content);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        if (reader != null)
                        {
                            reader.close();
                        }

                        if (socket != null)
                        {
                            socket.close();
                        }
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            System.out.println("server ternimated");

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
