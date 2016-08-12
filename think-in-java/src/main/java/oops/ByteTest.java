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
 * Create Author  : lili.zhang
 * Create Date    : 2016-08-12
 * Project        : desultory-essay
 * File Name      : ByteTest.java
 */
public class ByteTest
{
    public static void main(String[] args)
    {
        byte b = 5;
        System.out.println(Integer.toBinaryString(b));          //  0111
        System.out.println(Integer.toBinaryString(reverse(b))); //  1110
    }

    private static int reverse(int x)
    {
        int result = 0;
        for(byte i = 0; i < 4; i++)
        {
            result = (result << 1) + (1 & (x >> i));
        }
        return result;
    }
}
