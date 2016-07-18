/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section10;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-15
 * Project        : desultory-essay
 * File Name      : SuperTypeWildCards.java
 */
public class SuperTypeWildCards
{
    public static void writeTo(List<? super Apple> apples)
    {
        // <? super Apple> 可接纳Apple及其子类
        apples.add(new Apple());
        apples.add(new Jonathan());
//        nope
//        apples.add(new Fruit());
    }

    public static <T> void addToList(List<T> list, T item)
    {
        list.add(item);
    }

    public static <T> void addToList2(List<? super T> list, T item)
    {
        list.add(item);
    }

    public static void main(String[] args)
    {
        ArrayList<Fruit> list = new ArrayList<Fruit>();
        addToList(list, new Apple());

        ArrayList<Fruit> list2 = new ArrayList<Fruit>();
        addToList2(list2, new Apple());
        System.out.println();
    }
}
