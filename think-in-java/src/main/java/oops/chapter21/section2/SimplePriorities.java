/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-20
 * Project        : desultory-essay
 * File Name      : SimplePriorities.java
 */
public class SimplePriorities implements Runnable
{
    private int countdown = 5;

    private volatile double d;

    private int priority;

    public SimplePriorities(int priority)
    {
        this.priority = priority;
    }

    public void run()
    {
        Thread.currentThread().setPriority(priority);
        while (true)
        {
            for (int i = 0; i < 100000; i++)
            {
                d += (Math.PI + Math.E) / ((double) i);
                if (i % 1000 == 0)
                {
                    Thread.yield();
                }
            }

            System.out.println(this);
            if (--countdown == 0)
            {
                return;
            }
        }
    }

    @Override
    public String toString()
    {
        return Thread.currentThread() + ": " + countdown;
    }

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
        {
            executorService.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        executorService.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        executorService.shutdown();

        /*Thread[pool-1-thread-6,10,main]: 5
        Thread[pool-1-thread-6,10,main]: 4
        Thread[pool-1-thread-1,1,main]: 5
        Thread[pool-1-thread-2,1,main]: 5
        Thread[pool-1-thread-3,1,main]: 5
        Thread[pool-1-thread-5,1,main]: 5
        Thread[pool-1-thread-4,1,main]: 5
        Thread[pool-1-thread-6,10,main]: 3
        Thread[pool-1-thread-6,10,main]: 2
        Thread[pool-1-thread-6,10,main]: 1
        Thread[pool-1-thread-1,1,main]: 4
        Thread[pool-1-thread-2,1,main]: 4
        Thread[pool-1-thread-3,1,main]: 4
        Thread[pool-1-thread-5,1,main]: 4
        Thread[pool-1-thread-4,1,main]: 4
        Thread[pool-1-thread-1,1,main]: 3
        Thread[pool-1-thread-2,1,main]: 3
        Thread[pool-1-thread-3,1,main]: 3
        Thread[pool-1-thread-5,1,main]: 3
        Thread[pool-1-thread-4,1,main]: 3
        Thread[pool-1-thread-1,1,main]: 2
        Thread[pool-1-thread-2,1,main]: 2
        Thread[pool-1-thread-3,1,main]: 2
        Thread[pool-1-thread-5,1,main]: 2
        Thread[pool-1-thread-4,1,main]: 2
        Thread[pool-1-thread-2,1,main]: 1
        Thread[pool-1-thread-1,1,main]: 1
        Thread[pool-1-thread-5,1,main]: 1
        Thread[pool-1-thread-4,1,main]: 1
        Thread[pool-1-thread-3,1,main]: 1*/
    }
}
