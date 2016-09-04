/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p6;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-04
 * Project        : desultory-essay
 * File Name      : Philosopher.java
 */
public class Philosopher implements Runnable
{
    private Chopstick left;

    private Chopstick right;

    private final int id;

    private final int ponderFactor;

    private Random random = new Random(47);

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor)
    {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    private void pause() throws InterruptedException
    {
        if (ponderFactor == 0)
        {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                System.out.println(this + " thinking");
                pause();
                System.out.println(this + " grabbing right");
                right.take();
                System.out.println(this + " grabbing left");
                left.take();
                System.out.println(this + " eating");
                pause();
                right.drop();
                left.drop();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(this + " exiting via interrupt");
        }
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Philosopher{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
