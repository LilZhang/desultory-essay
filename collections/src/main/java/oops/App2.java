/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-09-19
 * Project        : desultory-essay
 * File Name      : App2.java
 */
public class App2
{
    public static void main(String[] args)
    {
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() != 25)
        {
            int i = (int) (Math.random() * 100);
            set.add(i);
        }

        StringBuilder sb = new StringBuilder();
        Iterator<Integer> iterator = set.iterator();

        while (iterator.hasNext())
        {
            sb.append(iterator.next()).append(", ");
        }

        System.out.println(sb.toString());
    }

}

