/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section4;

import java.util.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-12
 * Project        : desultory-essay
 * File Name      : NewUtils.java
 */
public class New
{
    public static <K,V> Map<K,V> map()
    {
        return new HashMap<K, V>();
    }

    public static <T> List<T> list()
    {
        return new ArrayList<T>();
    }

    public static <T> Set<T> set()
    {
        return new HashSet<T>();
    }

    public static void method(Map<Integer, String> map)
    {

    }

    public static <T> void method2(Collection<T> collection)
    {

    }

    public static void main(String[] args)
    {
        Map<String, Object> map = map();
        List<String> list = list();

        // ...这尼玛
        method(New.<Integer, String>map());
    }
}
