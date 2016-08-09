/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.section12.test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-31
 * Project        : desultory-essay
 * File Name      : TestAllReference.java
 */
public class TestAllReference
{
    public static void main(String[] args)
    {
        SoftReference<Object> sr = new SoftReference<Object>(new Object());
        System.gc();
        System.out.println(sr.get());

        WeakReference<Object> wr = new WeakReference<Object>(new Object());
        System.gc();
        System.out.println(wr.get());
    }
}
