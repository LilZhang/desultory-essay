/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-02
 * Project        : desultory-essay
 * File Name      : App29.java
 */
public class App29
{
    public static void main(String[] args)
    {
        int threadCount = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++)
        {
            executorService.execute(new Runnable()
            {
                public void run()
                {
                    int time = 2 + (int) (Math.random() * 10);
                    try
                    {
                        String name = Thread.currentThread().getName();
                        System.out.println(name + " sleep : " + time + "s");
                        TimeUnit.SECONDS.sleep(time);
                        countDownLatch.countDown();
                        System.out.println(name + " count down");
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            });

        }

        try
        {
            countDownLatch.await();
            System.out.println("ok, ternimated");
            executorService.shutdownNow();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
