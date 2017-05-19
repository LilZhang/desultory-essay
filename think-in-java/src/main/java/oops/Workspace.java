/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-15
 * Project        : desultory-essay
 * File Name      : Workspace.java
 */
public class Workspace
{
    private static Object MONITOR = new Object();

    private static volatile boolean flag;

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable()
        {
            public void run()
            {
                try
                {
                    synchronized (MONITOR)
                    {
                        while (!Thread.interrupted())
                        {
                            while (flag)
                            {
                                MONITOR.wait();
                            }
                            System.out.println("set true!");
                            TimeUnit.SECONDS.sleep(1);
                            flag = true;
                            MONITOR.notify();
                        }
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("interrupted");
                }
            }
        });

        executorService.execute(new Runnable()
        {
            public void run()
            {
                try
                {
                    synchronized (MONITOR)
                    {
                        while (!Thread.interrupted())
                        {
                            while (!flag)
                            {
                                MONITOR.wait();
                            }
                            System.out.println("set false!");
                            TimeUnit.SECONDS.sleep(1);
                            flag = false;
                            MONITOR.notify();
                        }
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("interrupted");
                }
            }
        });
    }
}
