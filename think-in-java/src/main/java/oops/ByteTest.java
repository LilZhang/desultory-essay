/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import java.text.DecimalFormat;

/**
 * Description:
 * <p/>
 * Create Author  : lili.zhang
 * Create Date    : 2016-08-12
 * Project        : desultory-essay
 * File Name      : ByteTest.java
 */
public class ByteTest
{
    public static void main(String[] args)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(3.5D);
        System.out.println(format);

    }
}
