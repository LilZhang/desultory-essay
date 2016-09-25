/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p6;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-04
 * Project        : desultory-essay
 * File Name      : Chopstick.java
 */
public class Chopstick
{
    private boolean taken = false;

    public synchronized void take() throws InterruptedException
    {
        while (taken)
        {
            wait();
        }
        taken = true;
    }

    public synchronized void drop()
    {
        taken = false;
        notifyAll();
    }
}
