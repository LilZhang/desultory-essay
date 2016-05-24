/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9.section3;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-24
 * Project        : desultory-essay
 * File Name      : Inter2.java
 */
public class Inter2
{
    interface B
    {
        void f();
    }

    public class BImpl implements B
    {
        public void f()
        {

        }
    }

    private class BImpl2 implements B
    {
        public void f()
        {

        }
    }

    interface C
    {
        void f();
    }

    public class CImpl implements C
    {
        public void f()
        {

        }
    }

    private class CImpl2 implements C
    {
        public void f()
        {

        }
    }

    private interface D
    {
        void f();
    }

    public class DImpl implements D
    {
        public void f()
        {

        }
    }

    private class DImpl2 implements D
    {
        public void f()
        {

        }
    }

    public D getD()
    {
        return new DImpl2();
    }

    public static class EImpl implements E
    {
        public void g()
        {

        }

        class EDImpl implements E.G
        {
            public void f()
            {
                
            }
        }
    }
}

interface E
{
    interface G
    {
        void f();
    }

    public interface H
    {
        void f();
    }

    void g();

    //    illegal
    /*private interface I
    {
        void f();
    }*/
}
