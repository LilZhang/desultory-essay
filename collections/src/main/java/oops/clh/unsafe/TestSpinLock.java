/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.clh.unsafe;

import oops.clh.MyLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-29
 * Project        : desultory-essay
 * File Name      : TestSpinLock.java
 */
public class TestSpinLock
{
    public static void main(String[] args)
    {
        final AtomicInteger cnt = new AtomicInteger(0);
        final MyLock lock = new SpinLock();

        try
        {
            TimeUnit.MILLISECONDS.sleep(10);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < 6; i++)
        {
            Runnable runnable = new Runnable()
            {
                public void run()
                {
                    lock.lock();

                    try
                    {
                        String threadName = Thread.currentThread().getName();
                        System.out.println(threadName + " start");
                        long millis = System.currentTimeMillis();
                        TimeUnit.MILLISECONDS.sleep(2000 + ((int) (Math.random() * 5000)));
                        System.out.println(threadName + ": " + (System.currentTimeMillis() - millis));
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("interrupted");
                    }
                    finally
                    {
                        lock.unlock();
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.setName("Thread " + cnt.incrementAndGet());
            thread.start();
        }
    }
}
