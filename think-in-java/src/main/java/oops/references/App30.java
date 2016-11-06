/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.concurrent.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-02
 * Project        : desultory-essay
 * File Name      : App30.java
 */
public class App30
{
    public static void main(String[] args)
    {
        int threadCount = 5;

        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, new Runnable()
        {
            int loopCount = 3, loop = 0;

            public void run()
            {
                if (loop <= loopCount)
                {
                    System.out.format("now round %d, go for next round.\n", loop++);
                }
                else
                {
                    System.out.println("now ternimate");
                    executorService.shutdownNow();
                }
            }
        });

        for (int i = 0; i < threadCount; i++)
        {
            executorService.execute(new Runnable()
            {
                public void run()
                {
                    String name = Thread.currentThread().getName();

                    try
                    {
                        while (true)
                        {
                            int time = (int) (Math.random() * 10 + 3);
                            TimeUnit.SECONDS.sleep(1);
                            System.out.format("%s sleep for %d seconds\n", name, time);
                            TimeUnit.SECONDS.sleep(time);
                            System.out.format("%s wake up\n", name);
                            cyclicBarrier.await();
                        }
                    }
                    catch (BrokenBarrierException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InterruptedException e)
                    {
                        System.out.format("thread %s interrupted\n", name);
                    }
                }
            });
        }
    }
}
