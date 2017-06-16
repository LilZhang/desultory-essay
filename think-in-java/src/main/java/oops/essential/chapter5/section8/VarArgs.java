/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.essential.chapter5.section8;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-20
 * Project        : desultory-essay
 * File Name      : VarArgs.java
 */
public class VarArgs
{
    public int countArgs(Object... args)
    {
        return args.length;
    }

    public int sumArgs(int... args)
    {
        int result = 0;
        for (int i : args)
        {
            result += i;
        }
        return result;
    }

    public void handleArgs(Object... args)
    {
        for (Object object : args)
        {
            // handle object
        }
    }

    public static void main(String[] args)
    {
        int[] iArray = new int[2];
        Integer[] integers = new Integer[2];
        System.out.println(iArray.getClass());  // class [I
        System.out.println(integers.getClass());// class [Ljava.lang.Integer;
    }
}
