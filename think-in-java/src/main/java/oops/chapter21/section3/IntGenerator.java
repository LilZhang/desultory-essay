/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section3;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-21
 * Project        : desultory-essay
 * File Name      : IntGenerator.java
 */
public abstract class IntGenerator
{
    // 需要被多个线程同时使用的变量需 volatile
    // 当然，由synchronized方法或synchronized代码块维护的变量
    // 可以不用volatile
    private volatile boolean canceled = false;

    public boolean isCanceled()
    {
        return canceled;
    }

    public void cancel()
    {
        canceled = true;
    }

    public abstract int next();
}
