/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.section12;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-31
 * Project        : desultory-essay
 * File Name      : WeakHashMapTest.java
 */

// puzzled
public class WeakHashMapTest
{
    public static void main(String[] args)
    {
        WeakHashMap<Element, Integer> map = new WeakHashMap<Element, Integer>();
        for (int i = 0; i < 1000; i++)
        {
            map.put(new Element(), i);
        }

        Element[] ea = new Element[1];

        for (Map.Entry<Element, Integer> entry : map.entrySet())
        {
            if (entry.getKey().equals(new Element(3)))
            {
                ea[0] = entry.getKey();
            }
        }

        System.gc();

        Element element = ea[0];
//        printMap(map);
    }

    private static <K, V> void printMap(Map<K, V> map)
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet())
        {
            sb.append(entry.getKey())
                    .append(" -> ")
                    .append(entry.getValue())
                    .append("\n");

        }

        System.out.println(sb.toString());
    }
}

class Element
{
    private static int counter = 0;

    private int id = ++counter;

    public Element()
    {
    }

    public Element(int id)
    {
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable
    {
        System.out.println("finalizing " + id);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Element))
        {
            return false;
        }

        Element element = (Element) o;

        return id == element.id;

    }

    @Override
    public int hashCode()
    {
        return id;
    }
}