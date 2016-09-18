/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-09-17
 * Project        : desultory-essay
 * File Name      : App6.java
 */
public class App6
{
    private static final char[] chars = ("abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
            .toCharArray();

    private static final Random random = new Random();

    public static void main(String[] args)
    {
        int length = 10;
        String[] strs1 = new String[length];
        String[] strs2 = new String[length];
        for (int i = 0; i < length; i++)
        {
            strs1[i] = genRandomStr(10);
            strs2[i] = genRandomStr(10);
        }

        System.out.println(Arrays.toString(strs1));
        System.out.println(Arrays.toString(strs2));

        System.out.println(Arrays.equals(strs1, strs2));

        Arrays.sort(strs1);
        System.out.println(Arrays.toString(strs1));

        Arrays.sort(strs1, Collections.<String>reverseOrder());
        System.out.println(Arrays.toString(strs1));

        Arrays.sort(strs1, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(strs1));
    }

    private static String genRandomStr(int length)
    {
        char[] c = new char[length];
        for (int i = 0; i < length; i++)
        {
            c[i] = chars[random.nextInt(chars.length)];
        }
        return new String(c);
    }
}
