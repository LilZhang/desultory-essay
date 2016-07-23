/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-22
 * Project        : desultory-essay
 * File Name      : AddableTest.java
 */
public class AddableTest
{
    public static void main(String[] args)
    {
        List<Base> list = new ArrayList<Base>();
        list.add(new Base());
        list.add(new Sub());

        CollectionAdapter<Base> collectionAdapter = new CollectionAdapter<Base>();
        fill(collectionAdapter, Base.class, 3);
        fill(collectionAdapter, Sub.class, 3);
        System.out.println();
    }

    public static <T> void fill(Addable<T> addable, Class<? extends T> clazz, int size)
    {
        try
        {
            for (int i = 0; i < size; i++)
            {
                addable.add(clazz.newInstance());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

interface Addable<T>
{
    void add(T obj);
}

class CollectionAdapter<T> implements Addable<T>
{
    private Collection<T> storage = new LinkedList<T>();

    public void add(T obj)
    {
        storage.add(obj);
    }
}

class Base
{

}

class Sub extends Base
{

}


