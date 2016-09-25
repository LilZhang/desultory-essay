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
 * File Name      : Finally.java
 */
public class Finally
{
    public static void main(String[] args)
    {
        /** output:
         method3 : invoke method2
         method2 : invoke method
         method : throw exception
         method : catch exception and rethrow
         method : finally
         method2 : catch exception and rethrow
         method2 : finally
         method3 : catch exception
         method3 : finally
         */

        // catch块执行之后紧跟对应finally块，再处理调用栈

        method3();
    }

    private static void method3()
    {
        try
        {
            System.out.println("method3 : invoke method2");
            method2();
        }
        catch (Exception e)
        {
            System.out.println("method3 : catch exception");
        }
        finally
        {
            System.out.println("method3 : finally");
        }
    }

    private static void method2() throws Exception
    {
        try
        {
            System.out.println("method2 : invoke method");
            method(true);
        }
        catch (Exception e)
        {
            System.out.println("method2 : catch exception and rethrow");
            throw e;
        }
        finally
        {
            System.out.println("method2 : finally");
        }
    }

    private static void method(boolean throwException) throws Exception
    {
        try
        {
            if (throwException)
            {
                System.out.println("method : throw exception");
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            System.out.println("method : catch exception and rethrow");
            throw e;
        }
        finally
        {
            System.out.println("method : finally");
        }
    }
}
