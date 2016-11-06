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
 * File Name      : App282.java
 */
public class App282
{
    public static void main(String[] args) throws IOException
    {
        final PipedWriter writer = new PipedWriter();
        final PipedReader reader = new PipedReader(writer);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new Runnable()
        {
            public void run()
            {
                try
                {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    while (true)
                    {
                        String s = bufferedReader.readLine();
                        if ("exit".equals(s))
                        {
                            System.out.println("bye");
                            writer.close();
                            reader.close();
                            System.exit(0);
                        }

                        System.out.println("send");
                        writer.write(((int) 'a'));
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
                        int read = reader.read();
                        System.out.println("echo: " + ((char) read));
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
