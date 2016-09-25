/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter3.section10;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-17
 * Project        : desultory-essay
 * File Name      : BitOperation.java
 */
public class BitOperation
{
    public static void main(String[] args)
    {
        System.out.println(Integer.toBinaryString(105));        // 1101001
        System.out.println(Integer.toBinaryString(106));        // 1101010
        System.out.println(Integer.toBinaryString(105 & 106));  // 1101000  // 都为1才有1
        System.out.println(Integer.toBinaryString(105 | 106));  // 1101011  // 有一个1就是1
        System.out.println(Integer.toBinaryString(105 ^ 106));  // 11       // 不同才有1
        System.out.println(Integer.toBinaryString(~ 106));      // 11111111111111111111111110010101

        System.out.println();

        int i = 115;
        System.out.println(Integer.toBinaryString(i));          // 1110011
        System.out.println(Integer.toBinaryString(i >>= 1));    // 111001
        System.out.println(Integer.toBinaryString(i >>= 1));    // 11100
        System.out.println(Integer.toBinaryString(i <<= 1));    // 111000
        System.out.println(Integer.toBinaryString(i <<= 1));    // 1110000

        System.out.println();

        int positive = 234, nagetive = -234;
        System.out.println(Integer.toBinaryString(positive));           // 11101010
        System.out.println(Integer.toBinaryString(positive >>>= 1));    // 1110101
        System.out.println(Integer.toBinaryString(positive >>>= 1));    // 111010

        System.out.println(Integer.toBinaryString(nagetive));           // 11111111111111111111111100010110
        System.out.println(Integer.toBinaryString(nagetive >>>= 1));    // 1111111111111111111111110001011
        System.out.println(Integer.toBinaryString(nagetive >>>= 1));    // 111111111111111111111111000101
    }
}
