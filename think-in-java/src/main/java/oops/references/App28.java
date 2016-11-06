/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-02
 * Project        : desultory-essay
 * File Name      : App28.java
 */
public class App28
{
    public static void main(String[] args) throws IOException
    {
        final PipedInputStream in = new PipedInputStream();
        final PipedOutputStream out = new PipedOutputStream(in);


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable()
        {
            public void run()
            {
                try
                {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    while (true)
                    {
                        String s = reader.readLine();
                        if ("exit".equals(s))
                        {
                            System.out.println("bye");
                            in.close();
                            out.close();

                            System.exit(0);
                        }

                        System.out.println("send");
                        out.write(65);
                    }

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });

        executorService.execute(new Runnable()
        {
            public void run()
            {
                while (true)
                {
                    try
                    {
                        int read = in.read();
                        System.out.println("echo: " + read);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
