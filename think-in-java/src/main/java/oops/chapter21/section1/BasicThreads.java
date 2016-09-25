/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section1;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-19
 * Project        : desultory-essay
 * File Name      : BasicThreads.java
 */
public class BasicThreads
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 5; i++)
        {
            new Thread(new LiftOff()).start();
        }
        System.out.println("asigin finished.");
    }
}


