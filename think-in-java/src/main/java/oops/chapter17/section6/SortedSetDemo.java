/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.section6;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-27
 * Project        : desultory-essay
 * File Name      : SortedSetDemo.java
 */
public class SortedSetDemo
{
    public static void main(String[] args)
    {
        Random random = new Random(47);
        SortedSet<SortedSetObject> sortedSet = new TreeSet<SortedSetObject>();
        for (int i = 0; i < 10; i++)
        {
            int index = random.nextInt(20);
            SortedSetObject sortedSetObject = new SortedSetObject(index);

            sortedSet.add(sortedSetObject);
        }

        System.out.println();

    }

    private static class SortedSetObject implements Comparable<SortedSetObject>
    {
        private int index;
        private int value;
        private int count;

        public SortedSetObject(int index)
        {
            this.index = index;
            this.value = index * index;
            this.count = index * index * index;
        }

        public int compareTo(SortedSetObject o)
        {
            return this.index - o.index;
        }

        public int getIndex()
        {
            return index;
        }

        public void setIndex(int index)
        {
            this.index = index;
        }

        public int getValue()
        {
            return value;
        }

        public void setValue(int value)
        {
            this.value = value;
        }

        public int getCount()
        {
            return count;
        }

        public void setCount(int count)
        {
            this.count = count;
        }
    }
}
