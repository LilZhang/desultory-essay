/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section4.p41;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-28
 * Project        : desultory-essay
 * File Name      : SynchronizedBlocked.java
 */
public class SynchronizedBlocked implements Runnable
{
    public synchronized void f()
    {
        while (true)
        {
            Thread.yield();     // never release lock
        }
    }

    public SynchronizedBlocked()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                f();        // lock acquired by this thread
            }
        }.start();
    }

    // 一个线程尝试获取另一个线程对相同对象的锁，因而阻塞。
    // 此时无法被中断
    public void run()
    {
        System.out.println("trying to call SynchronizedBlocked.f()..." +
                "already run in constructor");
        f();
        System.out.println("exiting SynchronizedBlocked.run()");
    }
}
