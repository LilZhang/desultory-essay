/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter12.section6;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-31
 * Project        : desultory-essay
 * File Name      : StackTraceTest.java
 */
public class StackTraceTest
{
    public static void main(String[] args)
    {
        try
        {
            exceptionMethod();
        }
        catch (Exception e)
        {
            StackTraceElement[] stackTrace = e.getStackTrace();

            e.printStackTrace();
        }
    }

    private static void exceptionMethod()
    {
        ExceptionClass.method();
    }

    public static class ExceptionClass
    {
        public static void method()
        {
            int[] iArray = {1,2,3};
            int i = iArray[4];
        }
    }
}
