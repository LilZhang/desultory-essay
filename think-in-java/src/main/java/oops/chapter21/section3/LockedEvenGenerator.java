/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-21
 * Project        : desultory-essay
 * File Name      : LockedEvenGenerator.java
 */
public class LockedEvenGenerator extends IntGenerator
{
    private int value = 0;

    private Lock lock = new ReentrantLock();

    @Override
    public int next()
    {
        lock.lock();        // 加锁
        try
        {
            ++value;
            ++value;
            return value;
        }
        finally
        {
            lock.unlock();  // 解锁
        }

    }

    public static void main(String[] args)
    {
        EvenChecker.test(new EvenGenerator());
    }
}
