/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sync.io.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-05
 * Project        : desultory-essay
 * File Name      : TcpIoServer.java
 */
public class TcpIoServer
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true)
        {
            Socket socket = serverSocket.accept();

            // 接收
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char c;
            StringBuilder sb = new StringBuilder();
            while ((c = ((char) bufferedReader.read())) != '\u0000')    // 将'\u0000'作为结尾
            {
                sb.append(c);
            }
            String received = sb.toString();
            System.out.println("[get]: " + received);


            // 发送
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello client!\u0000".getBytes());
            outputStream.flush();
            socket.close();

            if ("bye".equals(received))
            {
                break;
            }
        }
        System.out.println("bye");
    }
}
