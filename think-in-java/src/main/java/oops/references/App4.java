/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-09-17
 * Project        : desultory-essay
 * File Name      : App4.java
 */
public class App4
{
    public static void main(String[] args) throws Exception
    {
        try
        {
            throwException(AppException.class);
        }
        catch (AppException e)
        {
            throw e;
        }

        try
        {
            throwException(AppException.class);
        }
        catch (AppException e)
        {
            throw new UnsupportedOperationException(e);
        }

        try
        {
            throwException(AppException.class);
        }
        catch (AppException e)
        {
            throw ((Exception) new UnsupportedOperationException().initCause(e));
        }

        System.out.println("...");
        try
        {
            throwException(AppException.class);
        }
        catch (AppException e)
        {
            throw new IllegalArgumentException();
        }

        try
        {
            throwException(AppException.class);
        }
        catch (AppException e)
        {
            throw ((Exception) e.fillInStackTrace());
        }

    }

    private static <E extends Exception> void throwException(Class<E> exClass) throws E
    {

        try
        {
            throw exClass.newInstance();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}

class AppException extends Exception
{

}
