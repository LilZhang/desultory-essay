/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p2;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-04
 * Project        : desultory-essay
 * File Name      : SimpleCyclicBarrierDemo.java
 */

//CyclicBarrier: 一组线程都执行到了 await() 点时，再开始处理下一个动作。
public class SimpleCyclicBarrierDemo
{
    private static Random random = new Random();

    private static int counter = 0;

    private static int THREAD_NUM = 5;

    public static void main(String[] args)
    {
        final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM, new Runnable()
        {
            private int cyclicCount = 0;

            public void run()
            {
                if (cyclicCount < 3)
                {
                    System.out.println("all threads call await()...next round...");
                    System.out.println();
                    cyclicCount++;
                }
                else
                {
                    System.out.println("time to exit...");
                    executorService.shutdownNow();
                }
            }
        });


        for (int i = 0; i < THREAD_NUM; i++)
        {
            executorService.execute(new Runnable()
            {
                private int id = ++counter;

                public void run()
                {
                    try
                    {
                        while (!Thread.interrupted())
                        {
                            int t = 1000 + random.nextInt(2000);
                            TimeUnit.MILLISECONDS.sleep(t);
                            System.out.println(String.format("[%d]\tdo something...time: %d", id, t));

                            cyclicBarrier.await();
                        }
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("exiting via interrupt");
                    }
                    catch (BrokenBarrierException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    /*
    [3]	do something...time: 1008
    [2]	do something...time: 1015
    [5]	do something...time: 1119
    [1]	do something...time: 1735
    [4]	do something...time: 1803
    all threads call await()...next round...

    [4]	do something...time: 1326
    [1]	do something...time: 1473
    [5]	do something...time: 1715
    [2]	do something...time: 1801
    [3]	do something...time: 1803
    all threads call await()...next round...

    [1]	do something...time: 1055
    [5]	do something...time: 1333
    [2]	do something...time: 1658
    [3]	do something...time: 1774
    [4]	do something...time: 1964
    all threads call await()...next round...

    [5]	do something...time: 1002
    [1]	do something...time: 1010
    [2]	do something...time: 1025
    [4]	do something...time: 1792
    [3]	do something...time: 1985
    time to exit...
    */
}
