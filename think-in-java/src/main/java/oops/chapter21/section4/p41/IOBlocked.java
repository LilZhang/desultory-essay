/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section4.p41;

import java.io.IOException;
import java.io.InputStream;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-28
 * Project        : desultory-essay
 * File Name      : IOBlocked.java
 */
public class IOBlocked implements Runnable
{
    private InputStream in;

    public IOBlocked(InputStream in)
    {
        this.in = in;
    }

    // I/O 不可被中断
    public void run()
    {
        System.out.println("waiting for read().");
        try
        {
            in.read();
        }
        catch (IOException e)
        {
//            e.printStackTrace();
            if (Thread.currentThread().isInterrupted())
            {
                System.out.println("interrupted from blocked I/O");
            }
            else
            {
                throw new RuntimeException(e);
            }
        }
        System.out.println("exiting SynchronizedBlocked.run()");
    }
}
