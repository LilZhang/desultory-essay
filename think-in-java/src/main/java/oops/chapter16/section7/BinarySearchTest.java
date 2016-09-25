/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter16.section7;

import java.util.Arrays;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-25
 * Project        : desultory-essay
 * File Name      : BinarySearchTest.java
 */
public class BinarySearchTest
{
    private static int[] array = {7, 5, 4, 6, 9, 2, 3, 1, 0, -3, -5, -7};

    public static void main(String[] args)
    {
        int[] array = BinarySearchTest.array;

        // binarySearch之前必须先要排序
        Arrays.sort(array);

        // 返回搜索index
        int search = Arrays.binarySearch(array, 6);

        // 若没有结果
        // 返回[ - (插入点) - 1 ]
        // 插入点: 第一个大于查找元素的元素的index
        int search1 = Arrays.binarySearch(array, 8);

        System.out.println();
    }
}
