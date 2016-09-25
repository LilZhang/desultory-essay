/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter10.section6;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-28
 * Project        : desultory-essay
 * File Name      : AbstractBase.java
 */
public abstract class Base
{
    private int value;

    public Base(int value)
    {
        this.value = value;
    }

    protected int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public abstract int value();
}
