/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-01
 * Project        : desultory-essay
 * File Name      : App243.java
 */
public class App243
{
    private static int counter = 0;

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory()
        {
            public Thread newThread(Runnable r)
            {
                Thread thread = new Thread(r, "thread_from_factory_" + (++counter));
                return thread;
            }
        });

        for (int i = 0; i < 4; i++)
        {
            executorService.execute(new Runnable()
            {
                public void run()
                {
                    String name = Thread.currentThread().getName();

                    while (true)
                    {
                        try
                        {
                            System.out.println("thread name: " + name);
                            TimeUnit.SECONDS.sleep(2);
                        }
                        catch (InterruptedException e)
                        {

                            System.out.println(name + " interrupted");
                        }
                    }
                }
            });
        }

        executorService.shutdown();
    }
}
