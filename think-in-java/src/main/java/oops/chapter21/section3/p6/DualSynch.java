/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section3.p6;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-24
 * Project        : desultory-essay
 * File Name      : DualSynch.java
 */
public class DualSynch
{
    private Object syncObject = new Object();

    public synchronized void f()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println("f()");
            Thread.yield();         // 循环体最后使用
        }
    }

    public void g()
    {
        synchronized (syncObject)
        {
            for (int i = 0; i < 5; i++)
            {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args)
    {
        final DualSynch dualSynch = new DualSynch();
        new Thread()
        {
            @Override
            public void run()
            {
                dualSynch.f();
            }
        }.start();

        dualSynch.g();

        /*
        g()
        g()
        f()
        f()
        f()
        g()
        g()
        g()
        f()
        f()
        */
    }
}
