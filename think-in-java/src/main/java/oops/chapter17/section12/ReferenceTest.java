/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.section12;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-29
 * Project        : desultory-essay
 * File Name      : ReferenceTest.java
 */

// SoftReference, WeakReference和PhantomReference由强到弱。
// SoftReference用以实现内存敏感的高速缓存。
// WeakReference是为实现“规范映射”而设计的。
// PhantomReference用以调度回收前的清理工作。
public class ReferenceTest
{
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<VeryBig>();

    private static void checkQueue()
    {
        Reference<? extends VeryBig> poll = rq.poll();
        if (poll != null)
        {
            VeryBig veryBig = poll.get();
            System.out.println("In queue: " + veryBig);
        }
        else
        {
            System.out.println("null when checked");
        }
    }

    public static void main(String[] args)
    {
        int size = 10;
        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<SoftReference<VeryBig>>();
        for (int i = 0; i < size; i++)
        {
            sa.add(new SoftReference<VeryBig>(new VeryBig("Soft " + i), rq));
            System.out.println("Just created: " + sa.getLast());
            checkQueue();
            /*SoftReference<VeryBig> last = sa.getLast();
            VeryBig veryBig = last.get();
            System.out.println("lil check: " + veryBig);*/
        }

        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<WeakReference<VeryBig>>();
        for (int i = 0; i < size; i++)
        {
            wa.add(new WeakReference<VeryBig>(new VeryBig("Weak " + i), rq));
            System.out.println("Just created: " + wa.getLast());
            checkQueue();
        }

        SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("Soft"));
        WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig("Weak"));

        System.gc();
        System.out.println("gc");

        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<PhantomReference<VeryBig>>();
        for (int i = 0; i < size; i++)
        {
            pa.add(new PhantomReference<VeryBig>(new VeryBig("Phantom " + i), rq));
            System.out.println("Just created: " + pa.getLast());
            checkQueue();
        }
    }
}

class VeryBig
{
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;

    public VeryBig(String ident)
    {
        this.ident = ident;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("VeryBig{");
        sb.append("ident='").append(ident).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    protected void finalize() throws Throwable
    {
        System.out.println("finzlizing " + ident);
    }
}