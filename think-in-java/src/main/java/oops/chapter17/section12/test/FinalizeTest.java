/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.section12.test;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-29
 * Project        : desultory-essay
 * File Name      : FinalizeTest.java
 */
public class FinalizeTest
{
    public static void main(String[] args)
    {
        A a = new A();
        a.b = new B();

        a = null;

        System.gc();
    }
}

class A
{
    public B b;
    @Override
    protected void finalize() throws Throwable
    {
        System.out.println("now finalizing A  " + System.nanoTime());
    }
}

class B
{
    @Override
    protected void finalize() throws Throwable
    {
        System.out.println("now finalizing B  " + System.nanoTime());
    }
}