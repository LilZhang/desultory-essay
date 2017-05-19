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
 * File Name      : ShellSort.java
 */
public class ShellSort
{
    public static void shellSort(int[] array, int leftBound, int rightBound)
    {
        // 参数验证略去

        int length = rightBound - leftBound + 1;
        int h = 1;
        int nextH;
        while ((nextH = h * 3 + 1) < length)        // 获取间隔序列的最大值
        {
            h = nextH;
        }

        while (h > 0)
        {
            // 按照如下数组下标遍历(以下为下标)
            // (0, 4), (1, 5), (2, 6), ...,(3, 7) , (0, 4, 8), (1, 5, 9): (inner..., outer)
            // 显然, outer 从 间隔值 h 开始
            // inner 有一个或多个值，在 h-增量排序 必要时需遍历 inner
            for (int outer = h + leftBound; outer < length; outer++)
            {
                int outerTemp = array[outer];
                int innerTemp;
                int inner = outer;

                while (inner >= h + leftBound &&    // 后续使用的 inner 实际是 inner-h，所以要大于 h
                        (innerTemp = array[inner - h]) > outerTemp) // 当 inner 值大于 outer 时，需要进行 h-增量排序
                {                                   // 其实就是将 outer 值插入 h 序列的合适位置
                                                    // 并将 h 序列该位置之后的元素都向后移一位
                                                    // 若从数组角度看，应该是移 h 位
                    array[inner] = innerTemp;
                    inner -= h;
                }
                array[inner] = outerTemp;
            }
            h = (h - 1) / 3;                        // 降序遍历间隔序列，使用下一个增量值(间隔)
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
        shellSort(array, 0, length);

        System.out.println(Arrays.toString(array));
    }
}
