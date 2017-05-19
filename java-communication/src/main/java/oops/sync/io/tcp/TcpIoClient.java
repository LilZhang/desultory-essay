/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sync.io.tcp;

import java.io.*;
import java.net.Socket;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-05
 * Project        : desultory-essay
 * File Name      : TcpIoClient.java
 */
public class TcpIoClient
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("127.0.0.1", 8899);

        // 发送
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello server\u0000".getBytes());
        outputStream.flush();


        // 接收
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        char c;
        StringBuilder sb = new StringBuilder();
        while ((c = ((char) bufferedReader.read())) != '\u0000')
        {
            sb.append(c);
        }
        System.out.println("[get]: " + sb.toString());


        socket.close();
    }
}
