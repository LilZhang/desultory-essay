/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section10;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-19
 * Project        : desultory-essay
 * File Name      : Wildcards.java
 */
public class Wildcards
{
    static void rawArgs(Holder holder, Object arg)
    {
        holder.set(arg);    // warning
        Object o = holder.get();
    }

    static void unboundedArg(Holder<?> holder, Object arg)
    {
        //holder.set(arg);    // error
        Object o = holder.get();
    }

    static <T> T exact1(Holder<T> holder)
    {
        T t = holder.get();
        return t;
    }

    static <T> T exact2(Holder<T> holder, T arg)
    {
        holder.set(arg);
        T t = holder.get();
        return t;
    }

    static <T> void wildSubType(Holder<? extends T> holder, T arg)
    {
        //holder.set(arg);    // error
        T t = holder.get();
    }

    static <T> void wildSuperType(Holder<? super T> holder, T arg)
    {
        holder.set(arg);
        Object o = holder.get();
    }

    public static void main(String[] args)
    {
        Holder raw = new Holder<Long>();

        Holder<Long> qualified = new Holder<Long>();
        Holder<?> unbounded = new Holder<Long>();
        Holder<? extends Long> bounded = new Holder<Long>();
        Long lng = 1L;

        rawArgs(raw, lng);
        rawArgs(qualified, lng);
        rawArgs(unbounded, lng);
        rawArgs(bounded, lng);

        unboundedArg(raw, lng);
        unboundedArg(qualified, lng);
        unboundedArg(unbounded, lng);
        unboundedArg(bounded, lng);

        Object o = exact1(raw);
        Long aLong = exact1(qualified);
        Object o1 = exact1(unbounded);
        Long aLong1 = exact1(bounded);
    }


    private static class Holder<T>
    {
        private T value;

        public T get()
        {
            return value;
        }

        public void set(T value)
        {
            this.value = value;
        }
    }
}
