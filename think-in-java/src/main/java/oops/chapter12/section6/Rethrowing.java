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

        try
        {
            rethrowNewException();
        }
        catch (Exception e)
        {
            // 抛出新的异常，意味着启用新的调用栈
            e.printStackTrace();
        }

        try
        {
            rethrowNewExceptionAndInitCause();
        }
        catch (Throwable e)
        {
            // 抛出新的异常，经 initCause() 初始化后之前的调用栈将被保留
            // 打印调用栈会出现两批信息：新抛出的 + Caused by + 之前的
            // 若要抛出 new Exception 可用 throw new Exception(e);
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
            // 重新抛出异常时之前的调用栈信息保留
            throw e;
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
            // 调用 fillInStackTrace() 后 调用栈由此开始
            throw (Exception) e.fillInStackTrace();
        }
    }

    private static void rethrowNewException() throws IllegalArgumentException
    {
        try
        {
            exceptionThrowing();
        }
        catch (Exception e)
        {
            // 抛出新的异常，意味着启用新的调用栈
            throw new IllegalArgumentException();
        }
    }

    private static void rethrowNewExceptionAndInitCause() throws Throwable
    {
        try
        {
            exceptionThrowing();
        }
        catch (Exception e)
        {
            // 抛出新的异常，经 initCause() 初始化后之前的调用栈将被保留
            // 打印调用栈会出现两批信息：新抛出的 + Caused by + 之前的
            // 若要抛出 new Exception 可用 throw new Exception(e);
            throw new IllegalArgumentException().initCause(e);
        }
    }


    private static void exceptionThrowing() throws Exception
    {
        throw new Exception("throw origin exception");
    }
}
