/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.aqs.shared;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-03
 * Project        : desultory-essay
 * File Name      : Sync.java
 */
public class BooleanLatch
{
    private static class Sync extends AbstractQueuedSynchronizer
    {
        boolean isSignalled()
        {
            return getState() != 0;
        }

        protected int tryAcquireShared(int ignore)
        {
            return isSignalled() ? 1 : -1;
        }

        protected boolean tryReleaseShared(int ignore)
        {
            setState(1);
            return true;
        }
    }

    private final Sync sync = new Sync();

    public boolean isSignalled()
    {
        return sync.isSignalled();
    }

    public void signal()
    {
        sync.releaseShared(1);
    }

    public void await() throws InterruptedException
    {
        sync.acquireSharedInterruptibly(1);
    }
}
