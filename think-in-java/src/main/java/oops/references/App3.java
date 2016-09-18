/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.Iterator;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-17
 * Project        : desultory-essay
 * File Name      : App3.java
 */
public class App3
{


    public static void main(String[] args)
    {
        IterableObj<Integer> iterableObj =
                new IterableObj<Integer>(new Integer[]{1, 2, 3, 4});
        for (Integer integer : iterableObj)
        {
            System.out.println(integer);
        }
    }


}

class IterableObj<T> implements Iterable<T>
{
    private final T[] array;

    public IterableObj(T[] array)
    {
        this.array = array;
    }

    public Iterator<T> iterator()
    {
        return new Iterator<T>()
        {
            private int cursor = array.length - 1;

            public boolean hasNext()
            {
                return cursor >= 0;
            }

            public T next()
            {
                return array[cursor--];
            }

            public void remove()
            {
                throw new UnsupportedOperationException();
            }
        };
    }
}
