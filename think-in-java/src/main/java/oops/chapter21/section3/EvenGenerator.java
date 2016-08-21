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
 * File Name      : EvenGenerator.java
 */
public class EvenGenerator extends IntGenerator
{
    private int value = 0;

    @Override
    public int next()
    {
        ++value;
        ++value;    // 存在风险: 非原子操作 see:SynchronizedEvenGenerator.java
        return value;
    }

    public static void main(String[] args)
    {
        EvenChecker.test(new EvenGenerator());
    }
}
