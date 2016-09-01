/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p4;

import oops.chapter21.section1.LiftOff;

import java.util.concurrent.BlockingQueue;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-01
 * Project        : desultory-essay
 * File Name      : TestBlockingQueues.java
 */
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
    // TODO: 2016/9/1  
}
