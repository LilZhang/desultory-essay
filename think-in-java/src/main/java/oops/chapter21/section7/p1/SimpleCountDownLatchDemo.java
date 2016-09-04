/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-04
 * Project        : desultory-essay
 * File Name      : SimpleCountDownLatchDemo.java
 */

// CountDownLatch: countDown 到 0 之后，await() 后面的语句可以执行。
public class SimpleCountDownLatchDemo
{
    private static Random random = new Random();

    private static int counter = 0;

    private static synchronized int getId()
    {
        return ++counter;
    }

    private static int COUNT_DOWN_NUM = 9;

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_DOWN_NUM);
        final CountDownLatch countDownLatch = new CountDownLatch(COUNT_DOWN_NUM);

        for (int i = 0; i < COUNT_DOWN_NUM; i++)
        {
            executorService.execute(new Runnable()
            {
                private int id = getId();

                public void run()
                {
                    try
                    {
                        int t = 1000 + random.nextInt(2000);
                        TimeUnit.MILLISECONDS.sleep(t);
                        System.out.println(String.format("[%d]\tdo something...time: %d", id, t));
                        countDownLatch.countDown();
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("exiting via interrupt");
                    }

                }
            });
        }
        try
        {
            countDownLatch.await();
            System.out.println("count down to 0 -> Done");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        /*
        [3]	do something...time: 1046
        [9]	do something...time: 1057
        [5]	do something...time: 1250
        [7]	do something...time: 1529
        [4]	do something...time: 1726
        [1]	do something...time: 1789
        [8]	do something...time: 1791
        [2]	do something...time: 1912
        [6]	do something...time: 1927
        count down to 0 -> Done
        */
    }
}
