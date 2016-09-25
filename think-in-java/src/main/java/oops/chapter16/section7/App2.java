/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter16.section7;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-25
 * Project        : desultory-essay
 * File Name      : App2.java
 */
public class App2
{
    public static void main(String[] args)
    {
        Thin thin1 = new Thin();
        Thin thin2 = new Thin();

        Thin[] array1 = new Thin[8];
        for (int i = 0; i < 8; i++)
        {
            if (i % 2 == 0)
            {
                array1[i] = thin1;
            }
            else
            {
                array1[i] = thin2;
            }
        }

        System.out.println();

        Arrays.sort(array1);

        System.out.println();

//        Arrays.sort(array1, new ThinComparator());
        Arrays.sort(array1, Collections.<Thin>reverseOrder());

        System.out.println();
    }

    private static class Thin implements Comparable<Thin>
    {
        private static int counter = 0;
        private int id = ++counter;

        private String content = "origin";

        public void boost()
        {
            this.content = "boosted";
        }

        public int compareTo(Thin o)
        {
            return id - o.id;
        }
    }

    private static class ThinComparator implements Comparator<Thin>
    {
        public int compare(Thin o1, Thin o2)
        {
            return (o1.id - o2.id) * (-1);
        }
    }
}
