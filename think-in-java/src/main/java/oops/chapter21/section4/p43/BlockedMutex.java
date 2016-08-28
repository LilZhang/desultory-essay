/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section4.p43;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-28
 * Project        : desultory-essay
 * File Name      : BlockedMutex.java
 */

class Blocked2 implements Runnable
{
    BlockedMutex blockedMutex = new BlockedMutex(); // 获得锁

    public void run()
    {
        System.out.println("waiting for f() in BlockedMutex");
        blockedMutex.f();
        System.out.println("broken out of blocked call");
    }
}

public class BlockedMutex
{
    private Lock lock = new ReentrantLock();

    public BlockedMutex()
    {
        // 在当前线程获取该对象的锁
        lock.lock();
    }

    public void f()
    {
        try
        {
            // 在另一个线程获取该对象的锁(Lock)
            // 因而阻塞
            // 但仍然能被中断
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        }
        catch (InterruptedException e)
        {
            System.out.println("interrupted from lock acquisition in f()");
        }
    }

    public static void main(String[] args)
    {
        try
        {
            Thread thread = new Thread(new Blocked2());
            thread.start();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("issuing interrupt()");
            thread.interrupt();

            /*waiting for f() in BlockedMutex
            issuing interrupt()
            interrupted from lock acquisition in f()
            broken out of blocked call*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }




    }
}

