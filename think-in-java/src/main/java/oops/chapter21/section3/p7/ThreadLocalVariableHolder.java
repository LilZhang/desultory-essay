/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section3.p7;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-24
 * Project        : desultory-essay
 * File Name      : ThreadLocalVariableHolder.java
 */
public class ThreadLocalVariableHolder
{
    // 只需要一个 ThreadLocal 对象
    // 可以管理多进程中的值，每个值相互不受影响
    // #0: 254
    // #1: 7650
    // #2: 2494
    // #3: 82
    // #4: 3747
    // #0: 255
    // #1: 7651
    // #2: 2495
    // #3: 83
    // #4: 3748

    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>()
    {
        private Random random = new Random(47);

        @Override
        protected synchronized Integer initialValue()
        {
            return random.nextInt(10000);
        }
    };

    public static void increment()
    {
        value.set(value.get() + 1);
    }

    public static int get()
    {
        return value.get();
    }

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try
        {
            for (int i = 0; i < 5; i++)
            {
                executorService.execute(new Accessor(i));

            }
            TimeUnit.SECONDS.sleep(3);
            executorService.shutdownNow();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

class Accessor implements Runnable
{
    private final int id;

    public Accessor(int id)
    {
        this.id = id;
    }

    public void run()
    {
        while (!Thread.currentThread().isInterrupted())
        {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString()
    {
        return "#" + id + ": " + ThreadLocalVariableHolder.get();
    }
}
