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
 * Create Date    : 2017-01-28
 * Project        : desultory-essay
 * File Name      : Partition.java
 */
public class Partition
{
    public static int partition(int[] array, int leftBound, int rightBound, int pivot)
    {
        // 参数验证略去

        int leftCursor = leftBound - 1;
        int rightCursor = rightBound + 1;

        while (true)
        {
            while (array[++leftCursor] < pivot)     // 从左往右: 在发现大于基准值时跳出
            {
                // do nothing
            }

            while (array[--rightCursor] > pivot)     // 从右往左: 在发现小于基准值时跳出
            {
                // do nothing
            }

            if (leftCursor >= rightCursor)          // 指针重合或超过，停止
            {
                break;
            }
            else
            {
                int temp = array[leftCursor];       // 交换异常元素的位置
                array[leftCursor] = array[rightCursor];
                array[rightCursor] = temp;
            }
        }
        return leftCursor;
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
        System.out.println(partition(array, 0, length, array[length]));

        System.out.println(Arrays.toString(array));
    }
}
