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
 * Create Date    : 2017-01-27
 * Project        : desultory-essay
 * File Name      : MergeSort.java
 */
public class MergeSort
{
    public static void mergeSort(int[] array, int leftBound, int rightBound)
    {
        // 参数验证略去

        int[] workSpace = new int[rightBound - leftBound + 1];      // 归并排序需要一个额外的相同大小的操作数组来进行操作
        recMergeSort(array, workSpace, leftBound, rightBound);
    }

    private static void recMergeSort(int[] array, int[] workSpace, int leftBound, int rightBound)
    {
        if (leftBound == rightBound)                                // 如果递归到元素只有一个，不做处理，直接返回
        {
            return;
        }

        int mid = (leftBound + rightBound) / 2;                     // 将数组一分为二
        recMergeSort(array, workSpace, leftBound, mid);             // 并分别处理
        recMergeSort(array, workSpace, mid + 1, rightBound);        // 处理方式为再一分为二(递归调用)
        merge(array, workSpace, leftBound, mid, rightBound);        // 对已二分的数组进行归并
    }

    private static void merge(int[] array, int[] workSpace, int leftBound, int mid, int rightBound)
    {
        int i = 0;
        int leftCursor = leftBound;                                 // 分别从数组两个点开始
        int rightCursor = mid + 1;                                  // 一个从起点开始，一个从中点开始
                                                                    // 相当于将数组分作两半

        while (leftCursor <= mid && rightCursor <= rightBound)
        {
            workSpace[i++] = array[leftCursor] < array[rightCursor] ?
                    array[leftCursor++] : array[rightCursor++];     // 比较两半的大小，小的放进操作数组
        }

        while (leftCursor <= mid)                                   // 此处及以下的 while 是将比较后未经过比较的元素依次放入操作数组
        {
            workSpace[i++] = array[leftCursor++];
        }

        while (rightCursor <= rightBound)                           // 同上
        {
            workSpace[i++] = array[rightCursor++];
        }

        System.arraycopy(workSpace, 0, array, leftBound, i);        // 将操作数组中刚才排过序的元素拷贝至原数组
                                                                    // 准备下一次更大粒度的归并
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
        mergeSort(array, 0, length);

        System.out.println(Arrays.toString(array));
    }
}
