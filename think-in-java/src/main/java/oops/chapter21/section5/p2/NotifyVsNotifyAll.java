/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p2;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-31
 * Project        : desultory-essay
 * File Name      : NotifyVsNotifyAll.java
 */

class Blocker
{
    synchronized void waitingCall()
    {
        try
        {
            while (!Thread.interrupted())
            {
                wait();
                System.out.println(Thread.currentThread() + " ");
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Exiting via interrupt");
        }
    }

    synchronized void prod()
    {
        notify();
    }

    synchronized void prodAll()
    {
        notifyAll();
    }
}

class Task implements Runnable
{
    static Blocker blocker = new Blocker();

    public void run()
    {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable
{
    static Blocker blocker = new Blocker();

    public void run()
    {
        blocker.waitingCall();
    }
}

public class NotifyVsNotifyAll
{
    public static void main(String[] args) throws Exception
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
        {
            executorService.execute(new Task());
        }
        executorService.execute(new Task2());

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            boolean prod = true;

            @Override
            public void run()
            {
                if (prod)
                {
                    System.out.println("\nnotify()");
                    Task.blocker.prod();
                    prod = false;
                }
                else
                {
                    System.out.println("\nnotifyAll()");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);   // run every .4 second
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("\nTimer canceled");

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Task2.blocker.prodAll()");
        Task2.blocker.prodAll();

        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        executorService.shutdownNow();
    }
}

        /*
        notify()
        Thread[pool-1-thread-1,5,main]

        notifyAll()
        Thread[pool-1-thread-1,5,main]
        Thread[pool-1-thread-3,5,main]
        Thread[pool-1-thread-4,5,main]
        Thread[pool-1-thread-5,5,main]
        Thread[pool-1-thread-2,5,main]

        notify()
        Thread[pool-1-thread-1,5,main]

        notifyAll()
        Thread[pool-1-thread-1,5,main]
        Thread[pool-1-thread-2,5,main]
        Thread[pool-1-thread-5,5,main]
        Thread[pool-1-thread-4,5,main]
        Thread[pool-1-thread-3,5,main]

        notify()
        Thread[pool-1-thread-1,5,main]

        notifyAll()
        Thread[pool-1-thread-1,5,main]
        Thread[pool-1-thread-3,5,main]
        Thread[pool-1-thread-4,5,main]
        Thread[pool-1-thread-5,5,main]
        Thread[pool-1-thread-2,5,main]

        notify()
        Thread[pool-1-thread-1,5,main]

        notifyAll()
        Thread[pool-1-thread-1,5,main]
        Thread[pool-1-thread-2,5,main]
        Thread[pool-1-thread-5,5,main]
        Thread[pool-1-thread-4,5,main]
        Thread[pool-1-thread-3,5,main]

        notify()
        Thread[pool-1-thread-1,5,main]

        notifyAll()
        Thread[pool-1-thread-1,5,main]
        Thread[pool-1-thread-3,5,main]
        Thread[pool-1-thread-4,5,main]
        Thread[pool-1-thread-5,5,main]
        Thread[pool-1-thread-2,5,main]

        notify()
        Thread[pool-1-thread-1,5,main]

        notifyAll()
        Thread[pool-1-thread-1,5,main]
        Thread[pool-1-thread-2,5,main]
        Thread[pool-1-thread-5,5,main]
        Thread[pool-1-thread-4,5,main]
        Thread[pool-1-thread-3,5,main]

        Timer canceled
        Task2.blocker.prodAll()
        Thread[pool-1-thread-6,5,main]

        Shutting down
        Exiting via interrupt
        Exiting via interrupt
        Exiting via interrupt
        Exiting via interrupt
        Exiting via interrupt
        Exiting via interrupt
        */
