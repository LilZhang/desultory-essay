/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.clh.advancedclh;

import oops.clh.unsafe.SpinLock;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-29
 * Project        : desultory-essay
 * File Name      : CLHLock.java
 */

// ?

public class AdvancedCLHLock
{
    private static final Unsafe unsafe;

    private static final long tailOffset;

    private static final long headOffset;

    static
    {
        try
        {
            unsafe = getUnsafeInstance();
            tailOffset = unsafe.objectFieldOffset(SpinLock.class.getDeclaredField("tail"));
            headOffset = unsafe.objectFieldOffset(SpinLock.class.getDeclaredField("head"));
        }
        catch (Exception e)
        {
            throw new Error(e);
        }
    }

    private volatile CLHNode tail;

    private volatile CLHNode head;

    public void lock(CLHNode currentThreadNode)
    {
        CLHNode prev = null;
        for (;;)
        {
            prev = tail;
            if (unsafe.compareAndSwapObject(this, tailOffset, tail, currentThreadNode))
            {
                break;
            }
        }

        if (prev != null)
        {
            while (prev.isLocked)
            {
            }
        }
    }

    public void unlock(CLHNode currentThreadNode)
    {
        if (!unsafe.compareAndSwapObject(this, tailOffset, currentThreadNode, null))
        {
            currentThreadNode.isLocked = false;
        }
    }

    private static Unsafe getUnsafeInstance() throws
            SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException
    {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }

    private static class CLHNode
    {
        private CLHNode prev;

        private CLHNode head;

        private boolean isLocked = true;
    }
}
