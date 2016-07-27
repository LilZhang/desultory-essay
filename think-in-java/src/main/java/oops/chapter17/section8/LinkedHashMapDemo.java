/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.section8;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-27
 * Project        : desultory-essay
 * File Name      : LinkedHashMapDemo.java
 */
public class LinkedHashMapDemo
{
    // LinkedHashMap 将 accessOrder 置为 true 后，
    // 被访问过的节点往后排
    // 最近最少使用(LRU)算法
    public static void main(String[] args)
    {
        LinkedHashMap<Integer, Character> linkedHashMap = new LinkedHashMap<Integer, Character>(10, 0.75F, true);
        char base = 'A';
        for (int i = 0; i < 10; i++)
        {
            linkedHashMap.put(i, base++);
        }

        // 0=A; 1=B; 2=C; 3=D; 4=E; 5=F; 6=G; 7=H; 8=I; 9=J;
        System.out.println(iterateMap(linkedHashMap));

        for (int i = 0; i < 4; i++)
        {
            // 0=A; 1=B; 2=C; 3=D;
            System.out.print(i + "=" + linkedHashMap.get(i) + "; ");
        }

        System.out.println();

        // 4=E; 5=F; 6=G; 7=H; 8=I; 9=J; 0=A; 1=B; 2=C; 3=D;
        System.out.println(iterateMap(linkedHashMap));
    }

    private static <K, V> String iterateMap(LinkedHashMap<K, V> linkedHashMap)
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : linkedHashMap.entrySet())
        {
            sb.append(entry);
            sb.append("; ");
        }
        return sb.toString();
    }
}
