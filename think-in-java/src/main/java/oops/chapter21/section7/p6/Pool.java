/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p6;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-06
 * Project        : desultory-essay
 * File Name      : Pool.java
 */

// 一个简单的对象池，用于演示 semaphore 的用法
    // Semaphore: 一个允许指定数量为N的若干个线程同时访问某个对象的 计数信号量
public class Pool<T>
{
    private final int size;

    private List<T> items = new ArrayList<T>();

    private volatile BitSet checkOut;

    private Semaphore semaphore;

    public Pool(Class<T> clazz, int size)
    {
        this.size = size;
        checkOut = new BitSet(size);
        semaphore = new Semaphore(size, true);

        for (int i = 0; i < size; i++)
        {
            try
            {
                items.add(clazz.newInstance());
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public T checkOut() throws InterruptedException
    {
        semaphore.acquire();    // 超出计数时阻塞
        return getItem();
    }

    public void checkIn(T t)
    {
        if (releaseItem(t))
        {
            semaphore.release();    // 释放
        }
    }

    private synchronized T getItem()
    {
        for (int i = 0; i < size; i++)
        {
            if (!checkOut.get(i))
            {
                checkOut.set(i);
                return items.get(i);
            }
        }
        return null;
    }

    private synchronized boolean releaseItem(T item)
    {
        int index = items.indexOf(item);
        if (index == -1)
        {
            return false;
        }
        if (checkOut.get(index))
        {
            checkOut.clear(index);
            return true;
        }
        return false;
    }
}
