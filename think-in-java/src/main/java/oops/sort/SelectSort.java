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
 * File Name      : SelectSort.java
 */
public class SelectSort
{
    public static void selectSort(int[] array, int leftBound, int rightBound)
    {
        // 参数验证略去

        for (int i = leftBound; i <= rightBound; i++)
        {
            int min = array[i];
            int minIndex = i;

            for (int j = i; j <= rightBound; j++)
            {
                if (min > array[j])
                {
                    min = array[j];     // 找出最小值
                    minIndex = j;       // 找出最小值所在位置
                }
            }

            array[minIndex] = array[i]; // 将最小值与第 n 个值替换
            array[i] = min;
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
        selectSort(array, 0, length);

        System.out.println(Arrays.toString(array));
    }
}
