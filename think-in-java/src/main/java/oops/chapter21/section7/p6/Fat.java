/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p6;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-06
 * Project        : desultory-essay
 * File Name      : Fat.java
 */
public class Fat
{
    private volatile double d;

    private static int counter = 0;

    private int id = ++counter;

    public Fat()
    {
        for (int i = 0; i < 10000; i++)
        {
            d += (Math.PI + Math.E) / (double)i;
        }
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Fat{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
