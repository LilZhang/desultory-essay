/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter16.section7;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-07-25
 * Project        : desultory-essay
 * File Name      : StringArraySort.java
 */
public class StringArraySort
{
    private static char[] chars = ("abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    static Random random = new Random(47);

    public static void main(String[] args)
    {
        String[] sa = new String[20];

        for (int i = 0; i < sa.length; i++)
        {
            char[] c = new char[5];
            for (int i1 = 0; i1 < c.length; i1++)
            {
                c[i1] = chars[random.nextInt(chars.length)];
            }

            sa[i] = new String(c);
        }

        // 原顺序
        System.out.println("Before sort: " + Arrays.toString(sa));
        String stringToSearch = sa[random.nextInt(20)];
        // 字母顺序 先大写后小写
        Arrays.sort(sa);
        System.out.println("After sort: " + Arrays.toString(sa));

        // 字母逆序
        Arrays.sort(sa, Collections.reverseOrder());
        System.out.println("Reverse sort: " + Arrays.toString(sa));

        // 字母顺序 不区分大小写 aABbcC
        Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
        System.out.println("CASE_INSENSITIVE_ORDER sort: " + Arrays.toString(sa));

        // 按指定Comparator排序并搜索
        int search = Arrays.binarySearch(sa, stringToSearch, String.CASE_INSENSITIVE_ORDER);
        System.out.println(search);
    }
}
