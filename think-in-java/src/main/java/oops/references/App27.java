/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-02
 * Project        : desultory-essay
 * File Name      : App27.java
 */
public class App27
{
    public static void main(String[] args)
    {
        final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new Runnable()
        {
            public void run()
            {
                while (true)
                {
                    try
                    {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        String s = reader.readLine();

                        if ("exit".equals(s))
                        {
                            System.out.println("bye");
                            System.exit(0);
                        }
                        System.out.println("send: yo");
                        blockingQueue.put("yo");
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("thread 1 interrupted");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
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
                        String take = blockingQueue.take();
                        System.out.println("echo: " + take);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("thread 2 interrupted");
                    }
                }
            }
        });
    }
}
