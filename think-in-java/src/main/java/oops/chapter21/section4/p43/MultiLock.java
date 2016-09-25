/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section4.p43;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-28
 * Project        : desultory-essay
 * File Name      : MultiLock.java
 */

// 同一个线程如何多次获取同一个对象的锁
public class MultiLock
{
    private static int lockNum = 0;

    private synchronized void f1(int count)
    {
        System.out.println("f1() 获取 MultiLock 的锁: " + (++lockNum));
        if (count-- > 0)
        {
            System.out.println("now f1() calling f2()");
            f2(count);
        }
        System.out.println("f1() 释放 MultiLock 的锁: " + (--lockNum));
    }

    private synchronized void f2(int count)
    {
        System.out.println("f2() 获取 MultiLock 的锁: " + (++lockNum));
        if (count-- > 0)
        {
            System.out.println("now f2() calling f1()");
            f1(count);
        }
        System.out.println("f2() 释放 MultiLock 的锁: " + (--lockNum));
    }

    public static void main(String[] args)
    {
        new MultiLock().f1(5);

        /*
        f1() 获取 MultiLock 的锁: 1
        now f1() calling f2()
        f2() 获取 MultiLock 的锁: 2
        now f2() calling f1()
        f1() 获取 MultiLock 的锁: 3
        now f1() calling f2()
        f2() 获取 MultiLock 的锁: 4
        now f2() calling f1()
        f1() 获取 MultiLock 的锁: 5
        now f1() calling f2()
        f2() 获取 MultiLock 的锁: 6
        f2() 释放 MultiLock 的锁: 5
        f1() 释放 MultiLock 的锁: 4
        f2() 释放 MultiLock 的锁: 3
        f1() 释放 MultiLock 的锁: 2
        f2() 释放 MultiLock 的锁: 1
        f1() 释放 MultiLock 的锁: 0
        */
    }
}
