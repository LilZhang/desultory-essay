/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-17
 * Project        : desultory-essay
 * File Name      : App2.java
 */
public class App2
{
}

class Outter
{
    private String id = "outter";

    public class Inner
    {
        public Outter getOutter()
        {
            return Outter.this;
        }

        public Inter getInter()
        {
            class InterImpl implements Inter
            {
                public void doSomething()
                {

                }
            }

            return new InterImpl();
        }

        public Inter getInter2()
        {
            return new Inter()
            {
                public void doSomething()
                {

                }
            };
        }
    }
}

interface Inter
{
    void doSomething();
}

class ExtendedInner extends Outter.Inner
{
    public ExtendedInner(Outter outter)
    {
        outter.super();
    }
}