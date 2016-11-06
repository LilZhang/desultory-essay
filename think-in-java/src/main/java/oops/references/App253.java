/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-02
 * Project        : desultory-essay
 * File Name      : App253.java
 */
public class App253
{
    private static int RANGE = 300;

    public static void main(String[] args)
    {
        final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>()
        {
            @Override
            protected Integer initialValue()
            {
                return ((int) (Math.random() * RANGE));
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
        {
            executorService.execute(new Runnable()
            {
                int count = 10;

                public void run()
                {
                    for (int i1 = 0; i1 < count; i1++)
                    {
                        Integer integer = threadLocal.get();
                        integer++;
                        System.out.format("# %s : %d\n", Thread.currentThread().getName(), integer);
                        threadLocal.set(integer);
                        Thread.yield();
                    }
                }
            });
        }
    }
}
