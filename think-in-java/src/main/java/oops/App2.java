/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-08-20
 * Project        : desultory-essay
 * File Name      : App2.java
 */
public class App2
{
    private static Set<Integer> set = new HashSet<Integer>();

    static
    {
        set.add(15);
        set.add(22);
        set.add(40);
        set.add(12);
        set.add(32);
        set.add(1);
        set.add(27);
        set.add(11);
        set.add(3);
        set.add(20);
        set.add(8);
        set.add(23);
        set.add(24);
        set.add(29);
        set.add(26);
        set.add(5);
        set.add(33);
        set.add(41);
        set.add(17);
        set.add(6);
        set.add(36);
    }

    public static void main(String[] args)
    {
        System.out.println(getNum());
    }

    private static int getNum()
    {
        int x = ((int) (Math.random() * 43)) + 1;
        if (set.contains(x))
        {
            return getNum();
        }
        return x;
    }
}