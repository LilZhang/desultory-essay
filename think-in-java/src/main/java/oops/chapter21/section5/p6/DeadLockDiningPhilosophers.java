/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-04
 * Project        : desultory-essay
 * File Name      : DeadLockDiningPhilosophers.java
 */
public class DeadLockDiningPhilosophers
{
    public static void main(String[] args) throws InterruptedException
    {
        int ponder = 5, size = 5;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++)
        {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++)
        {
            // may cause deadlock
            executorService.execute(new Philosopher(
                    chopsticks[i], chopsticks[(i + 1) % size], i, ponder));
        }

        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}
