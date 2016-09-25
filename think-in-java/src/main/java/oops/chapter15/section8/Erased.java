/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section8;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-14
 * Project        : desultory-essay
 * File Name      : Erased.java
 */
public class Erased<T>
{
    private Class<T> clazz;

    public Erased(Class<T> clazz)
    {
        this.clazz = clazz;
    }

    public T[] createArray(int size)
    {
        // 正确方式
        return (T[]) Array.newInstance(clazz, size);
    }

    public List<T> createList()
    {
        return new ArrayList<T>();
    }
}
