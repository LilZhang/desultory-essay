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
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-20
 * Project        : desultory-essay
 * File Name      : UsingThreadFactory.java
 */
public class UsingThreadFactory
{
    public static void main(String[] args) throws Exception
    {
        ExecutorService executorService = Executors
                .newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0; i < 10; i++)
        {
            executorService.execute(new DaemonRunnable());
        }
        TimeUnit.MILLISECONDS.sleep(222);
    }
}

class DaemonRunnable implements Runnable
{
    public void run()
    {
        try
        {
            while (true)
            {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + ": " + this);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

// Runnable后续处理
class DaemonThreadFactory implements ThreadFactory
{
    public Thread newThread(Runnable r)
    {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
