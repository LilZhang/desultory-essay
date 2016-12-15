/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.socket;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : Flag.java
 */
public class Flag
{
    private volatile static Flag instance;

    private volatile boolean run;

    public static Flag getInstance()
    {
        if (instance == null)
        {
            synchronized (Flag.class)
            {
                if (instance == null)
                {
                    instance = new Flag();
                }
            }
        }
        return instance;
    }

    private Flag()
    {

    }

    public boolean isRun()
    {
        return run;
    }

    public void setRun(boolean run)
    {
        this.run = run;
    }
}
