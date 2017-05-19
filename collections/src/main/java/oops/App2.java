/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-09-19
 * Project        : desultory-essay
 * File Name      : App2.java
 */
public class App2
{
    public static void main(String[] args)
    {
        System.out.println(returnABoolean());
    }

    private static boolean returnABoolean()
    {
        try
        {
            return false;
        }
        finally
        {
            System.out.println("ok?");
        }
    }
}

