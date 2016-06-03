/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter12.section12;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-03
 * Project        : desultory-essay
 * File Name      : RuntimeExceptionCatapult.java
 */
public class RuntimeExceptionCatapult
{
    public static void catapult(int param)
    {
        try
        {
            switch (param)
            {
                case 0: throw new FileNotFoundException();
                case 1: throw new IOException();
                case 2: throw new RuntimeException();
                default: return;
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    public static void catapult2()
    {
        throw new RuntimeException(new IllegalArgumentException());
    }

    public static void main(String[] args)
    {
//        catapult2();

        try
        {
            catapult2();
        }
        catch (RuntimeException e)
        {
            System.out.println("....");
            e.getCause().printStackTrace();
        }

        try
        {
            catapult2();
        }
        catch (RuntimeException e)
        {
            try
            {
                throw e.getCause();
            }
            catch (IllegalArgumentException e1)
            {
                System.out.println("IllegalArgumentException");
                e.printStackTrace();
            }
            catch (Throwable t)
            {
                System.out.println("Throwable");
                t.printStackTrace();
            }
        }
    }
}
