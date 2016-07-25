/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter16.section7;

import java.util.Arrays;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-25
 * Project        : desultory-essay
 * File Name      : ComparingArrays.java
 */
public class ComparingArrays
{
    public static void main(String[] args)
    {
        int[] a1 = new int[10];
        Arrays.fill(a1, 22);
        int[] a2 = new int[10];
        Arrays.fill(a2, 22);

        // Arrays.equals()
        // 1. 元素个数必须相等
        // 2. 对应的元素必须equals
        System.out.println(Arrays.equals(a1, a2));

        String[] s1 = new String[5];
        Arrays.fill(s1, "Hi");
        String[] s2 = new String[]{
                new String("Hi"),
                new String("Hi"),
                new String("Hi"),
                new String("Hi"),
                new String("Hi")
        };

        System.out.println(Arrays.equals(s1, s2));
    }
}
