/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section12;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-20
 * Project        : desultory-essay
 * File Name      : SelfBounded.java
 */
public class SelfBounded<T extends SelfBounded<T>>
{
    T element;

    SelfBounded<T> set(T arg)
    {
        this.element = arg;
        return this;
    }

    T get()
    {
        return element;
    }
}
