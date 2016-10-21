/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-21
 * Project        : desultory-essay
 * File Name      : ReferObj.java
 */
public class ReferObj
{
    private static int counter = 0;

    private int id;

    public ReferObj()
    {
        id = counter++;
        System.out.println("now initalizing " + this);
    }

    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("ReferObj{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    protected void finalize() throws Throwable
    {
        System.out.println("now finalizing " + this);
    }
}
