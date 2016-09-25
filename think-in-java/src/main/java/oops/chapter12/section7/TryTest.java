/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter12.section7;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-01
 * Project        : desultory-essay
 * File Name      : TryTest.java
 */
public class TryTest
{
    private static String method(int type)
    {
        try
        {
            if (type == 0)
            {
                throw new Exception();
            }

            // do not work cause of finally block
            return "value " + type;
        }
        catch (Exception e)
        {

            // do not work cause of finally block
            return "value exception";
        }
        finally
        {
            return "value finally";
        }
    }

    public static void main(String[] args)
    {
        System.out.println(method(1));  // "value finally"
        System.out.println(method(0));  // "value finally"
    }
}
