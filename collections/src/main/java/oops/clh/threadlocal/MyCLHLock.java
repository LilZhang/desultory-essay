/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.clh.threadlocal;

import oops.clh.MyLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-27
 * Project        : desultory-essay
 * File Name      : MyCLHLock.java
 */
public class MyCLHLock implements MyLock
{
    ThreadLocal<QNode> myNode = new ThreadLocal<QNode>()
    {
        @Override
        protected QNode initialValue()
        {
            return new QNode();
        }
    };

    ThreadLocal<QNode> myPred = new ThreadLocal<QNode>()
    {
        @Override
        protected QNode initialValue()
        {
            return null;
        }
    };

    AtomicReference<QNode> tail = new AtomicReference<QNode>(new QNode());

    public void lock()
    {
        QNode node = myNode.get();
        node.setLocked(true);
        QNode pred = tail.getAndSet(node);
        myPred.set(pred);
        while (myPred.get().isLocked())
        {

        }
    }

    public void unlock()
    {
        QNode node = myNode.get();
        node.setLocked(false);
        myNode.set(myPred.get());
    }

    static class QNode
    {
        private boolean locked;

        public boolean isLocked()
        {
            return locked;
        }

        public void setLocked(boolean locked)
        {
            this.locked = locked;
        }
    }
}
