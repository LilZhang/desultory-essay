/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-03
 * Project        : desultory-essay
 * File Name      : App34.java
 */
public class App34
{
    public static void main(String[] args)
    {
        ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(2);
        schedule.schedule(new Runnable()
        {
            public void run()
            {
                System.out.println("lo");
            }
        }, 8, TimeUnit.SECONDS);

        schedule.scheduleAtFixedRate(new Runnable()
        {
            public void run()
            {
                System.out.println("yo");
            }
        }, 3, 2, TimeUnit.SECONDS);
    }
}
