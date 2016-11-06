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
 * Create Date    : 2016-11-01
 * Project        : desultory-essay
 * File Name      : App242.java
 */
public class App242
{
    private static Thread thread1;

    private static Thread thread2;

    public static void main(String[] args)
    {
        thread1 = new Thread()
        {
            int countDown = 5;

            @Override
            public void run()
            {
                while (!Thread.interrupted() && countDown >= 0)
                {
                    try
                    {
                        System.out.println("thread 1 count down : " + countDown--);
                        TimeUnit.SECONDS.sleep(2);

                        if (countDown == 3 && thread2 != null)
                        {
                            thread2.join();
                        }
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("thread 1 interrupted in try");
                    }
                }

                System.out.println("thread 1 ternimated");
            }
        };

        thread2 = new Thread()
        {
            int countDown = 5;

            @Override
            public void run()
            {
                while (!Thread.interrupted() && countDown >= 0)
                {
                    try
                    {
                        System.out.println("thread 2 count down : " + countDown--);
                        TimeUnit.SECONDS.sleep(2);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("thread 2 interrupted in try");
                    }
                }

                System.out.println("thread 2 ternimated");
            }
        };

        thread1.start();
        thread2.start();
    }
}
