/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter12.section7;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-01
 * Project        : desultory-essay
 * File Name      : TestRuntimeException.java
 */
public class TestRuntimeException
{
    public static void main(String[] args)
    {
        g3();
    }

    private static void g3()
    {
        g4();
    }

    private static void g4()
    {
        g5();
    }

    private static void g5()
    {
        throw new RuntimeException("oops");
    }
}
