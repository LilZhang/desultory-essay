/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter2.session;

import oops.chapter8.session.MySessionBindingObj;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-03
 * Project        : desultory-essay
 * File Name      : MySessionObj.java
 */
public class MySessionObj extends MySessionBindingObj implements Serializable
{
    public static final long serialVersionUID = 1L;

    private final String sessionId;

    private final AtomicInteger count = new AtomicInteger(0);

    public MySessionObj(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public int getCount()
    {
        return count.get();
    }

    public int increaseCount()
    {
        return count.incrementAndGet();
    }
}
