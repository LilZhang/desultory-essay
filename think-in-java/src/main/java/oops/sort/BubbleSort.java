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
 * File Name      : BubbleSort.java
 */
public class BubbleSort
{
    public static void bubbleSort(int[] array, int leftBound, int rightBound)
    {
        // 参数验证略去

        for (int i = leftBound; i < rightBound - 1; i++)
        {
            for (int j = leftBound; j < rightBound - 1; j++)
            {
                if (array[j] > array[j + 1])    // 比较相邻元素的值
                {
                    int temp = array[j + 1];    // 若左项大于右项，则两值交换
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
            --rightBound;                       // 退出内循环时最后的元素已经有序，可以不做处理
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
        bubbleSort(array, 0, length);

        System.out.println(Arrays.toString(array));
    }
}
