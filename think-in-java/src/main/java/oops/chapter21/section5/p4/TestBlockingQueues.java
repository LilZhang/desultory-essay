/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p4;

import oops.chapter21.section1.LiftOff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-01
 * Project        : desultory-essay
 * File Name      : TestBlockingQueues.java
 */

// 关于同步队列 BlockingQueue:
    // 实现大致有 LinkedBlockingQueue, ArrayBlockingQueue 和 SynchronousQueue
    // 任何时刻只允许一个线程读写

class LiftOffRunner implements Runnable
{
    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> rockets)
    {
        this.rockets = rockets;
    }

    public void add(LiftOff liftOff)
    {
        try
        {
            rockets.put(liftOff);
        }
        catch (InterruptedException e)
        {
            System.out.println("interrupt during put()");
        }
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                LiftOff rocket = rockets.take();
                rocket.run();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("waking from take()");
        }
        System.out.println("exiting LiftOffRunner");
    }
}

public class TestBlockingQueues
{
    static void getKey()
    {
        try
        {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    static void getKey(String message)
    {
        System.out.println(message);
        getKey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue)
    {
        System.out.println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread thread = new Thread(runner);
        thread.start();
        for (int i = 0; i < 5; i++)
        {
            runner.add(new LiftOff());
        }
        getKey("Press 'Enter' (" + msg + ")");
        thread.interrupt();
        System.out.println("Finish " + msg + " test");
    }

    public static void main(String[] args)
    {
        test("LinkedBlockingQueue", new LinkedBlockingQueue<LiftOff>());    // Unlimited size
        test("ArrayBlockingQueue", new ArrayBlockingQueue<LiftOff>(3));     // Fixed size
        test("SynchronousQueue", new SynchronousQueue<LiftOff>());        // Size of 1
    }
}
