/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9.section8;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-26
 * Project        : desultory-essay
 * File Name      : NestingInterface.java
 */
public class NestingInterfaceClass
{
    // 自动为 static
    interface B
    {
        void b();
    }

    public interface C
    {
        void c();
    }

    private interface D
    {
        void d();
    }

    public class BImpl implements B
    {
        public void b()
        {

        }
    }

    private class BImpl2 implements B
    {
        public void b()
        {

        }
    }

    class CImpl implements C
    {
        public void c()
        {

        }
    }

    private class CImpl2 implements C
    {
        public void c()
        {

        }
    }

    private class DImpl implements D
    {
        public void d()
        {

        }
    }

    public class DImpl2 implements D
    {
        public void d()
        {

        }
    }

    public D getD()
    {
        return new DImpl2();
    }

    private D dref;

    public void receiveD(D d)
    {
        dref = d;
        dref.d();
    }
}
