/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section4.p41;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-28
 * Project        : desultory-essay
 * File Name      : Interrupting.java
 */
public class Interrupting
{
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static void test(Runnable r) throws InterruptedException
    {
        Future<?> future = executorService.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        String name = r.getClass().getSimpleName();
        System.out.println("now trying to interrupt " + name);
        future.cancel(true);
        System.out.println("interrupt has been sent to " + name);
    }

    public static void main(String[] args)
    {
        try
        {
            test(new SleepBlocked());
            test(new IOBlocked(System.in));     //interrupt failed
            test(new SynchronizedBlocked());    //interrupt failed
            TimeUnit.MILLISECONDS.sleep(3);
            System.out.println("aborting with System.exit(0)");
            System.exit(0);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        /*
        now trying to interrupt SleepBlocked
        interrupt has been sent to SleepBlocked
        InterruptedException of SleepBlocked        // SleepBlocked 中断成功
        exiting SleepBlocked.run()
        waiting for read().
        now trying to interrupt IOBlocked
        interrupt has been sent to IOBlocked        // 然而 IOBlocked 中断没有了下文，中断失败
        trying to call SynchronizedBlocked.f()...already run in constructor
        now trying to interrupt SynchronizedBlocked
        interrupt has been sent to SynchronizedBlocked  // 然而 SynchronizedBlocked 中断没有了下文，中断失败
        aborting with System.exit(0)
        */
    }
}
