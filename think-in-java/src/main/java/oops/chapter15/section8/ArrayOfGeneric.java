/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section8;

import java.lang.reflect.Array;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-06-14
 * Project        : desultory-essay
 * File Name      : ArrayOfGeneric.java
 */
public class ArrayOfGeneric
{
    static final int SIZE = 100;

    static Generic<Integer>[] gia;

    public static void main(String[] args)
    {
        // 不正确方式
        gia = (Generic<Integer>[]) new Generic[SIZE];

        System.out.println(gia.getClass().getSimpleName());
    }

    public <T> T[] createArray(Class<T> clazz, int size)
    {
        // 正确方式
        return (T[]) Array.newInstance(clazz, size);
    }


}

class Generic<T>
{
    private T field;

    public T getField()
    {
        return field;
    }

    public void setField(T field)
    {
        this.field = field;
    }
}
