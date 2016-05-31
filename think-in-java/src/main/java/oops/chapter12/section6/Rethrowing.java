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
 * File Name      : Rethrowing.java
 */
public class Rethrowing
{
    public static void main(String[] args)
    {
        try
        {
            rethrow();
        }
        catch (Exception e)
        {
            // 重新抛出异常时之前的调用栈信息保留
            e.printStackTrace();
        }

        System.out.println(" === ");

        try
        {
            rethrowButFillIn();
        }
        catch (Exception e)
        {
            // 调用 fillInStackTrace() 后 调用栈由此开始
            e.printStackTrace();
        }
    }

    private static void rethrow() throws Exception
    {
        try
        {
            exceptionThrowing();
        }
        catch (Exception e)
        {
            throw e;
            // 重新抛出异常时之前的调用栈信息保留
        }
    }

    private static void rethrowButFillIn() throws Exception
    {
        try
        {
            exceptionThrowing();
        }
        catch (Exception e)
        {
            throw (Exception) e.fillInStackTrace();
            // 调用 fillInStackTrace() 后 调用栈由此开始
        }
    }


    private static void exceptionThrowing() throws Exception
    {
        throw new Exception("throw origin exception");
    }
}
