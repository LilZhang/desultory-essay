/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p3;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-05
 * Project        : desultory-essay
 * File Name      : TimeUnitConvertTest.java
 */
public class TimeUnitConvertTest
{
    public static void main(String[] args)
    {
        long convert = TimeUnit.NANOSECONDS.convert(3000, TimeUnit.MILLISECONDS);

        System.out.println();
    }
}
