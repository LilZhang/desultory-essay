/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter16.section4;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-24
 * Project        : desultory-essay
 * File Name      : MultiDimensionalPrimitiveArray.java
 */
public class MultiDimensionalPrimitiveArray
{
    public static void main(String[] args)
    {
        int[][] a = {{1, 2, 3}, {4, 5, 6, 7, 8, 9}};
        int[][][] b = new int[2][3][4];
        int[][][] c = new int[3][][];

        int[][][] strings = (int[][][]) Array.newInstance(int.class, 2, 2, 2);

        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.deepToString(b));
        System.out.println(Arrays.deepToString(c));

    }

    private static <T> void f1(T t)
    {

    }
}
