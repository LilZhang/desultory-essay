/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-31
 * Project        : desultory-essay
 * File Name      : App24.java
 */
public class App24
{
    public static void main(String[] args)
    {
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                System.out.println("thread run");
                try
                {
                    TimeUnit.SECONDS.sleep(1);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                throw new IllegalArgumentException("what");
            }
        };

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
        {
            public void uncaughtException(Thread t, Throwable e)
            {
                System.out.println("uncaught exception found in thread " + t.getName());
                System.out.println("exception message : " + e.getMessage());
            }
        });

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
        {
            public void uncaughtException(Thread t, Throwable e)
            {
                // TODO: 16-11-1 handle exception here
            }
        });

        thread.start();
    }
}
