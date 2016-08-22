/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section3;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-22
 * Project        : desultory-essay
 * File Name      : AtomicIntegerTest.java
 */
public class AtomicIntegerTest implements Runnable
{
    // 原子 Integer 操作
    // 可适当减少 synchronized
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public int getValue()
    {
        return atomicInteger.get();
    }

    private void doubleIncrement()
    {
        atomicInteger.addAndGet(2);
    }

    public void run()
    {
        while (true)
        {
            doubleIncrement();
        }
    }

    public static void main(String[] args)
    {
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                System.out.println("aborting...");
                System.exit(0);
            }
        }, 8000);

        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicIntegerTest test = new AtomicIntegerTest();
        executorService.execute(test);
        executorService.shutdown();

        while (true)
        {
            int value = test.getValue();
            if (value % 2 != 0)
            {
                System.out.println(value);
                System.exit(0);
            }
        }
    }
}
