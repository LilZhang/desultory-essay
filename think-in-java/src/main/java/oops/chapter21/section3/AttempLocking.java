/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-21
 * Project        : desultory-essay
 * File Name      : AttempLocking.java
 */
public class AttempLocking
{
    private ReentrantLock lock = new ReentrantLock();

    public void untimed()
    {
        boolean captured = lock.tryLock();
        try
        {
            System.out.println("tryLock(): " + captured);
        }
        finally
        {
            if (captured)
            {
                lock.unlock();
            }
        }
    }

    public void timed()
    {
        boolean captured = false;
        try
        {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " +
                    captured);
        }
        finally
        {
            if (captured)
            {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args)
    {
        final AttempLocking al = new AttempLocking();
        al.untimed();
        al.timed();

        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                al.lock.lock(); // doesn't work ????
                System.out.println("acquired.");
            }
        });
        thread.setDaemon(true);
        thread.start();

        Thread.yield();

        al.untimed();
        al.timed();
    }
}
