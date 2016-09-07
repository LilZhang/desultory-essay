/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p5;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-06
 * Project        : desultory-essay
 * File Name      : SimpleScheduledThreadPoolExecutorDemo.java
 */
public class SimpleScheduledThreadPoolExecutorDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(3);

        final long begin = System.currentTimeMillis();
        scheduled.schedule(new Runnable()
        {
            public void run()
            {
                System.out.println("time spent: " + (System.currentTimeMillis() - begin));
            }
        }, 2, TimeUnit.SECONDS);

        scheduled.schedule(new Runnable()
        {
            public void run()
            {
                System.out.println("time spent: " + (System.currentTimeMillis() - begin));
            }
        }, 4, TimeUnit.SECONDS);

        scheduled.schedule(new Runnable()
        {
            public void run()
            {
                System.out.println("time spent: " + (System.currentTimeMillis() - begin));
            }
        }, 6, TimeUnit.SECONDS);

        scheduled.scheduleAtFixedRate(new Runnable()
        {
            public void run()
            {
                System.out.println("yo");
            }
        }, 1000, 500, TimeUnit.MILLISECONDS);

        TimeUnit.SECONDS.sleep(10);
        scheduled.shutdownNow();
    }
}
