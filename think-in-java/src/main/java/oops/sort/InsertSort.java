/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sort;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-26
 * Project        : desultory-essay
 * File Name      : InsertSort.java
 */
public class InsertSort
{
    public static void insertSort(int[] array, int leftBound, int rightBound)
    {
        // 参数验证略去

        for (int i = leftBound + 1; i <= rightBound; i++)
        {
            int insert = array[i];                      // 获取标记位元素
            for (int j = i - 1; j >= leftBound; j--)    // 反向遍历标记位前的有序数组部分
            {
                if (insert > array[j])                  // 若在有序数组部分中找到小于自己的元素
                {                                       // 说明为该元素位置，将之后的有序数组向后移一位
                    System.arraycopy(array, j + 1, array, j + 2, i - j - 1);
                    array[j + 1] = insert;              // 将该元素插入至该位置
                    break;
                }
                else if (j == leftBound)                // 若没有在有序数组部分中找到小于自己的元素
                {                                       // 说明该元素为有序数组部分的头部，需将有序数组整体向后移一位
                    System.arraycopy(array, j, array, j + 1, i - j);
                    array[j] = insert;                  // 将该元素插入至头部
                    break;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        int[] array = new int[15];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = ((int) (Math.random() * 100));
        }

        System.out.println(Arrays.toString(array));

        int length = array.length - 1;
        insertSort(array, 0, length);

        System.out.println(Arrays.toString(array));
    }
}
