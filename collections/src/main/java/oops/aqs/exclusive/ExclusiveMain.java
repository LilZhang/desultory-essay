/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.aqs.exclusive;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-29
 * Project        : desultory-essay
 * File Name      : ExclusiveMain.java
 */
public class ExclusiveMain
{
    public static void main(String[] args)
    {
        final Sync sync = new Sync();

        for (int i = 0; i < 4; i++)
        {
            Thread thread = new Thread(new Runnable()
            {
                public void run()
                {
                    sync.acquire();

                    try
                    {
                        long millis = System.currentTimeMillis();
                        String name = Thread.currentThread().getName();
                        System.out.println(name + " running...");

                        TimeUnit.MILLISECONDS.sleep(2000 + ((int)(Math.random() * 3000)));

                        System.out.println("time spent: " + (System.currentTimeMillis() - millis));
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        sync.release();
                    }


                }
            });
            thread.setName("my-thread-" + (i+1));
            thread.start();
        }
    }
}
