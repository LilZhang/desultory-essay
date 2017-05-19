/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.aqs.exclusive;

import oops.aqs.MySync;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-29
 * Project        : desultory-essay
 * File Name      : Sync.java
 */
public class Sync extends AbstractQueuedSynchronizer implements MySync
{
    @Override
    protected boolean tryAcquire(int arg)
    {
        if (compareAndSetState(0, 1))
        {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg)
    {
        if (getState() == 0)
        {
            throw new IllegalMonitorStateException();
        }
        setExclusiveOwnerThread(null);
        setState(0);

        return true;
    }

    public void acquire()
    {
        acquire(1);
    }

    public void release()
    {
        release(1);
    }
}
