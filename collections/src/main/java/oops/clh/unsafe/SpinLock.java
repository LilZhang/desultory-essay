/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.clh.unsafe;

import oops.clh.MyLock;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-29
 * Project        : desultory-essay
 * File Name      : SpinLock.java
 */
public class SpinLock implements MyLock
{
    private static Unsafe unsafe = null;

    private static final long valueOffset;

    private volatile int value = 0;

    static
    {
        try
        {
            unsafe = getUnsafeInstance();
            valueOffset = unsafe.objectFieldOffset(SpinLock.class.getDeclaredField("value"));
        }
        catch (Exception e)
        {
            throw new Error(e);
        }
    }

    public void lock()
    {
        for (;;)
        {
            int newV = value + 1;
            if (newV == 1)
            {
                if (unsafe.compareAndSwapInt(this, valueOffset, 0, newV))
                {
                    return;
                }
            }
        }
    }

    public void unlock()
    {
        unsafe.compareAndSwapInt(this, valueOffset, 1, 0);
    }

    private static Unsafe getUnsafeInstance() throws
            SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException
    {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
}
