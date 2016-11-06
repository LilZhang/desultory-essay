/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-03
 * Project        : desultory-essay
 * File Name      : App35.java
 */
public class App35
{
    public static void main(String[] args)
    {
        int nThreads = 8;
        final SemaphoreObj semaphoreObj = new SemaphoreObj();
        final CountDownLatch countDownLatch = new CountDownLatch(nThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < nThreads; i++)
        {
            executorService.execute(new Runnable()
            {
                public void run()
                {
                    int time = ((int) (Math.random() * 10 + 5));
                    semaphoreObj.access(time);
                    countDownLatch.countDown();
                }
            });
        }

        try
        {
            countDownLatch.await();
            System.out.println(semaphoreObj.getValue());
            executorService.shutdownNow();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }

    private static class SemaphoreObj
    {
        private AtomicInteger value = new AtomicInteger(0);

        private Semaphore semaphore = new Semaphore(3, true);

        public AtomicInteger getValue()
        {
            return value;
        }

        public void access(int time)
        {
            String name = Thread.currentThread().getName();
            try
            {
                semaphore.acquire();

                System.out.println(name + " coming in");

                TimeUnit.SECONDS.sleep(time);
                value.addAndGet(1);

                System.out.println(name + " getting out after " + time);
            }
            catch (InterruptedException e)
            {
                System.out.println(name + " interrupted");
            }
            finally
            {
                semaphore.release();
            }
        }
    }
}
