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
 * File Name      : SynchronizedEvenGenerator.java
 */
public class SynchronizedEvenGenerator extends IntGenerator
{
    private int value = 0;

    @Override
    public synchronized int next()
    {
        ++value;        // 已同步
        ++value;        // 也可以使用Lock对象。see: LockedEvenGenerator.java
        return value;
    }

    public static void main(String[] args)
    {
        EvenChecker.test(new EvenGenerator());
    }
}
