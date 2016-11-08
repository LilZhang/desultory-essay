/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

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
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args)
    {
        roundUpToPowerOf2(3);

        System.out.println();
    }

    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }
}