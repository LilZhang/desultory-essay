/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-21
 * Project        : desultory-essay
 * File Name      : EvenChecker.java
 */
public class EvenChecker implements Runnable
{
    private IntGenerator intGenerator;
    private final int id;

    public EvenChecker(IntGenerator intGenerator, int id)
    {
        this.intGenerator = intGenerator;
        this.id = id;
    }

    public void run()
    {
        while (!intGenerator.isCanceled())
        {
            int val = intGenerator.next();
            if (val % 2 != 0)
            {
                System.out.println(val + " not even!");
                intGenerator.cancel();
            }
        }
    }

    public static void test(IntGenerator intGenerator, int count)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++)
        {
            executorService.execute(new EvenChecker(intGenerator, i));
        }
        executorService.shutdown();
    }

    public static void test(IntGenerator intGenerator)
    {
        test(intGenerator, 10);
    }
}
