/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-17
 * Project        : desultory-essay
 * File Name      : App5.java
 */
public class App5
{
    public static void main(String[] args)
    {
        try
        {
            String[] array = getArray(String.class);
            String[][][] arrays = getArrays(String.class);
            System.out.println();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static <T> T[] getArray(Class<T> clazz) throws Exception
    {
        T[] ts = (T[]) Array.newInstance(clazz, 10);
        System.out.println(Arrays.toString(ts));
        return ts;
    }

    private static <T> T[][][] getArrays(Class<T> clazz) throws Exception
    {
        T[][][] ts = (T[][][]) Array.newInstance(clazz, 10, 10, 10);
        System.out.println(Arrays.deepToString(ts));
        return ts;
    }
}
