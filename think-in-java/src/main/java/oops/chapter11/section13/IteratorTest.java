/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter11.section13;

import java.util.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-30
 * Project        : desultory-essay
 * File Name      : IteratorTest.java
 */
public class IteratorTest
{
    public static void main(String[] args)
    {
        ReversibleArrayList<String> stringList = new ReversibleArrayList<String>();

        for (String string : stringList)
        {
            // TODO: 2016/5/30 handle
        }

        // 添加了自定义的 Iterable 获得方法，可在foreach中使用
        // reversed() 为反序
        for (String string : stringList.reversed())
        {
            // TODO: 2016/5/30 handle
        }

        // randomized() 为随机乱序
        for (String string : stringList.randomized())
        {
            // TODO: 2016/5/30 handle
        }
    }

    public static class ReversibleArrayList<E> extends ArrayList<E>
    {
        public ReversibleArrayList()
        {
            super();
        }

        public ReversibleArrayList(Collection<? extends E> c)
        {
            super(c);
        }

        // 添加了自定义的 Iterable 获得方法，可在foreach中使用
        public Iterable<E> reversed()
        {
            return new Iterable<E>()
            {
                public Iterator<E> iterator()
                {
                    return new Iterator<E>()
                    {
                        int current = size() - 1;

                        public boolean hasNext()
                        {
                            return current > -1;
                        }

                        public E next()
                        {
                            return get(current--);
                        }

                        public void remove()
                        {
                            throw new UnsupportedOperationException();
                        }
                    };
                }
            };
        }

        // 添加了自定义的 Iterable 获得方法，可在foreach中使用
        public Iterable<E> randomized()
        {
            return new Iterable<E>()
            {
                public Iterator<E> iterator()
                {
                    List<E> shuffled = Arrays.asList(
                            (E[]) ReversibleArrayList.this.toArray());
                    Collections.shuffle(shuffled, new Random(47));
                    return shuffled.iterator();
                }
            };
        }
    }
}
