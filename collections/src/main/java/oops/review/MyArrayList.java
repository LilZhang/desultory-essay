/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.review;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-01
 * Project        : desultory-essay
 * File Name      : MyArrayList.java
 */
public class MyArrayList<T> implements List<T>, Serializable, Cloneable
{
    private static final long serialVersionUID = 666L;

    private static final Object[] ELEMENT_DATA = {};

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elementData = ELEMENT_DATA;

    private int size = 0;

    private int modCount = 0;

    public MyArrayList()
    {
    }

    public MyArrayList(int len)
    {
        Utils.validLength(len);
        elementData = new Object[len];
    }

    public MyArrayList(Collection<? extends T> c)
    {
        addAll(c);
        // TODO: 16-12-1 size ?
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public boolean contains(Object o)
    {
        if (o != null)
        {
            for (Object element : elementData)
            {
                if (o.equals(element))
                {
                    return true;
                }
            }
        }
        else
        {
            for (Object element : elementData)
            {
                if (element == null)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public Iterator<T> iterator()
    {
        // TODO: 16-12-1 iterator
        return null;
    }

    public Object[] toArray()
    {
        Object[] objects = ELEMENT_DATA;
        if (size > 0)
        {
            objects = new Object[size];
            objects = Arrays.copyOf(elementData, size);
        }
        return objects;
    }

    public <T1> T1[] toArray(T1[] a)
    {
        if (a.length < size)
        {
            a = (T1[]) Array.newInstance(a.getClass(), size);
        }
        return ((T1[])Arrays.copyOf(elementData, size, a.getClass()));
    }

    public boolean add(T t)
    {
        if (elementData == ELEMENT_DATA)
        {
            elementData = new Object[DEFAULT_CAPACITY];
        }

        int len = elementData.length;
        if (len == Utils.CEILING)
        {
            throw new IllegalStateException("full array");
        }
        if (size == len)
        {
            int toSize = len << 1 + len;
            if (toSize - Utils.CEILING > 0)
            {
                toSize = Utils.CEILING;
            }
            elementData = Arrays.copyOf(elementData, toSize);
        }
        modCount++;
        return true;
    }

    public boolean remove(Object o)
    {
        int i = indexOf(o);
        if (i == -1)
        {
            return false;
        }
        System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
        elementData[--size] = null;
        modCount++;
        return true;
    }

    public boolean containsAll(Collection<?> c)
    {
        if (c == null)
        {
            return false;
        }
        if (c.size() == 0)
        {
            return true;
        }
        for (Object object : c)
        {
            if (indexOf(object) == -1)
            {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends T> c)
    {

        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c)
    {
        return false;
    }

    public boolean removeAll(Collection<?> c)
    {
        return false;
    }

    public boolean retainAll(Collection<?> c)
    {
        return false;
    }

    public void clear()
    {

    }

    public T get(int index)
    {
        return null;
    }

    public T set(int index, T element)
    {
        return null;
    }

    public void add(int index, T element)
    {

    }

    public T remove(int index)
    {
        return null;
    }

    public int indexOf(Object o)
    {
        if (o != null)
        {
            for (int i = 0; i < size; i++)
            {
                if (o.equals(elementData[i]))
                {
                    return i;
                }
            }
        }
        else
        {
            for (int i = 0; i < size; i++)
            {
                if (elementData[i] == null)
                {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o)
    {
        if (o != null)
        {
            for (int i = size - 1; i >= 0; i--)
            {
                if (o.equals(elementData[i]))
                {
                    return i;
                }
            }
        }
        else
        {
            for (int i = size - 1; i >= 0; i--)
            {
                if (elementData[i] == null)
                {
                    return i;
                }
            }
        }
        return -1;
    }

    public ListIterator<T> listIterator()
    {
        return null;
    }

    public ListIterator<T> listIterator(int index)
    {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex)
    {
        return null;
    }
}
