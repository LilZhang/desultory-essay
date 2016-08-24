/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section3.p5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-22
 * Project        : desultory-essay
 * File Name      : PairManager1.java
 */
public class CriticalSectionCompare
{
    public static class PairManager1 extends CriticalSectionCompareTest.PairManager
    {
        // synchronized 不属于方法签名
        // 所以可以在 Override 的时候加入

        // 试验一: 全方法锁
        @Override
        public synchronized void increment()
        {
            p.incrementX();
            p.incrementY();
            store(getPair());
        }
    }

    public static class PairManager2 extends CriticalSectionCompareTest.PairManager
    {
        // 试验二: 利用临界区锁部分方法
        @Override
        public void increment()
        {
            CriticalSectionCompareTest.Pair temp;
            synchronized (this)
            {
                p.incrementX();
                p.incrementY();
                temp = getPair();
            }
            store(temp);
        }
    }

    public static class ExplicitPairManager1 extends CriticalSectionCompareTest.PairManager
    {
        private Lock lock = new ReentrantLock();

        @Override
        public void increment()
        {
            lock.lock();
            try
            {
                p.incrementX();
                p.incrementY();
                store(getPair());
            }
            finally
            {
                lock.unlock();
            }

        }
    }

    public static class ExplicitPairManager2 extends CriticalSectionCompareTest.PairManager
    {
        private Lock lock = new ReentrantLock();

        @Override
        public void increment()
        {
            CriticalSectionCompareTest.Pair tmp;
            lock.lock();
            try
            {
                p.incrementX();
                p.incrementY();
                tmp = getPair();
            }
            finally
            {
                lock.unlock();
            }
            store(tmp);
        }
    }
}
