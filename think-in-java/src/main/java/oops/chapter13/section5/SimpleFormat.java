/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter13.section5;

import java.util.Formatter;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-04
 * Project        : desultory-essay
 * File Name      : SimpleFormat.java
 */
public class SimpleFormat
{
    private static Formatter formatter;

    public static void main(String[] args)
    {
        String s = "name";
        int x = 5;
        double y = 5.55;

        System.out.printf("result %s : [%d %f]\n", s, x, y);
        String format = String.format("result %s : [%d %f]\n", s, x, y);

        System.out.format("result %s : [%d %f]\n", s, x, y);

        formatter = new Formatter(System.out);
        formatter.format("result %s : [%d %f]\n", s, x, y);
    }
}
