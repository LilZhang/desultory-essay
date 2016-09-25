/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-21
 * Project        : desultory-essay
 * File Name      : ExceptionThread2.java
 */
public class ExceptionThread2 implements Runnable
{
    public void run()
    {
        Thread thread = Thread.currentThread();
        System.out.println("run() by " + thread);
        System.out.println("eh = " +
                thread.getUncaughtExceptionHandler());

        throw new RuntimeException("from ExceptionThread2");
    }

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool(
                new MyThreadFactory());
        executorService.execute(new ExceptionThread2());
        executorService.shutdown();

        /*oops.chapter21.section2.MyThreadFactory@5c64a871 creating new thread
        created Thread[Thread-0,5,main]
        eh = oops.chapter21.section2.MyUncaughtExceptionHandler@115c6cb
        run() by Thread[Thread-0,5,main]
        eh = oops.chapter21.section2.MyUncaughtExceptionHandler@115c6cb
        caught java.lang.RuntimeException: from ExceptionThread2*/
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler
{
    public void uncaughtException(Thread t, Throwable e)
    {
        System.out.println("caught " + e);
    }
}

class MyThreadFactory implements ThreadFactory
{
    public Thread newThread(Runnable r)
    {
        System.out.println(this + " creating new thread");
        Thread thread = new Thread(r);
        System.out.println("created " + thread);
        thread.setUncaughtExceptionHandler(
                new MyUncaughtExceptionHandler());
        System.out.println("eh = " +
                thread.getUncaughtExceptionHandler());

        return thread;
    }
}
