/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter5.section8;

import java.util.Arrays;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-19
 * Project        : desultory-essay
 * File Name      : ArrayTest.java
 */
public class ArrayTest
{
    public static void main(String[] args)
    {
        int[] i1 = {1, 2, 3};   // [1, 2, 3]
        int[] i2 = new int[3];  // [0, 0, 0]
        System.out.println(Arrays.toString(i1));
        System.out.println(Arrays.toString(i2));

        Integer[] i3 = {new Integer(1), new Integer(2), 3};     // [1, 2, 3]
        Integer[] i4 = new Integer[] {new Integer(4), new Integer(5), 6};   // [4, 5, 6]
        Integer[] i5 = new Integer[3];                                      // [null, null, null]
        System.out.println(Arrays.toString(i3));
        System.out.println(Arrays.toString(i4));
        System.out.println(Arrays.toString(i5));
    }
}
