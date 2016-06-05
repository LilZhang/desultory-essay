/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter13.section6;

import java.util.regex.Pattern;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-05
 * Project        : desultory-essay
 * File Name      : TestPattern.java
 */
public class TestPattern
{
    public static void main(String[] args)
    {
        Pattern.matches("a\\wc", "abc");    //true

        Pattern p = Pattern.compile("a\\wc");
        String[] strings = p.split("string1ascstring2avcstring3");

        String replaceFirst = "string1ascstring2avcstring3".replaceFirst("a\\wc", "kkk");

        System.out.println();
    }
}
