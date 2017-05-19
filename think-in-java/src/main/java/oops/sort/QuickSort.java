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
 * File Name      : QuickSort.java
 */
public class QuickSort
{
    public static void quickSort(int[] array, int leftBound, int rightBound)
    {
        // 参数验证略去

        recQuickSort(array, leftBound, rightBound);
    }

    private static void recQuickSort(int[] array, int leftBound, int rightBound)
    {
        int length = rightBound - leftBound + 1;
        if (length <= 3)                                // 若数组长度小于等于 3，可以通过比较来排序
        {
            manualSort(array, leftBound, rightBound);
            return;
        }

        int mid = partition(array, leftBound, rightBound);  // 若数组长度大于3，划分数组
        recQuickSort(array, leftBound, mid - 1);            // 并递归调用方法
        recQuickSort(array, mid + 1, rightBound);
    }

    private static int partition(int[] array, int leftBound, int rightBound)
    {
        int pivot = medianOf3(array, leftBound, rightBound);
        int leftCursor = leftBound;                     // 最左边的元素已经过“三数据项取中”时划分
        int rightCursor = rightBound - 1;               // 最右边两个元素已经过“三数据项取中”时划分，无需再处理

        while (true)
        {
            while (array[++leftCursor] < pivot)
            {
                // do nothing
            }

            while (array[--rightCursor] > pivot)
            {
                // do nothing
            }

            if (leftCursor >= rightCursor)
            {
                break;
            }
            else
            {
                swap(array, leftCursor, rightCursor);
            }
        }
        swap(array, leftCursor, rightBound - 1);            // 将保存的 pivot 替换至中间位置，这个位置将是该元素的最终位置
        return leftCursor;
    }

    private static int medianOf3(int[] array, int leftBound, int rightBound)
    {
        int mid = (leftBound + rightBound) / 2;
                                                // 将“三数据项取中”的三个元素按照大小
        if (array[leftBound] > array[mid])      // 最小的放左边，最大的放右边，中值放中间
        {
            swap(array, leftBound, mid);
        }

        if (array[leftBound] > array[rightBound])
        {
            swap(array, leftBound, rightBound);
        }

        if (array[mid] > array[rightBound])
        {
            swap(array, mid, rightBound);
        }

        swap(array, mid, rightBound - 1);       // 最后将中值置于右边倒数第二个位置，便于后续处理
        return array[rightBound - 1];
    }

    private static void swap(int[] array, int id1, int id2)
    {
        int temp = array[id1];
        array[id1] = array[id2];
        array[id2] = temp;
    }

    private static void manualSort(int[] array, int leftBound, int rightBound)
    {
        int length = rightBound - leftBound + 1;
        switch (length)
        {
            case 3:
                int mid = leftBound + 1;

                if (array[leftBound] > array[mid])
                {
                    swap(array, leftBound, mid);
                }

                if (array[leftBound] > array[rightBound])
                {
                    swap(array, leftBound, rightBound);
                }

                if (array[mid] > array[rightBound])
                {
                    swap(array, mid, rightBound);
                }

                break;

            case 2:
                if (array[leftBound] > array[rightBound])
                {
                    swap(array, leftBound, rightBound);
                }

                break;

            case 1:
                return;
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
        quickSort(array, 0, length);

        System.out.println(Arrays.toString(array));
    }
}
