/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-21
 * Project        : desultory-essay
 * File Name      : App.java
 */
public class SoftReferenceDemo
{
    // output:
    // now initalizing ReferObj{id=0}
    // get reference object before gc ReferObj{id=0}
    // get reference object after gc ReferObj{id=0}
    public static void main(String[] args) throws InterruptedException
    {
        ReferenceQueue<ReferObj> referenceQueue = new ReferenceQueue<ReferObj>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ReferLoader<ReferObj>(referenceQueue));
        executorService.shutdown();

        SoftReference<ReferObj> softReference = new SoftReference<ReferObj>(new ReferObj(), referenceQueue);

        System.out.println("get reference object before gc " + getReferObj(softReference));

        TimeUnit.SECONDS.sleep(2);
        System.gc();
        TimeUnit.MICROSECONDS.sleep(1);

        // get reference object after gc ReferObj{id=0}
        // GC 过后，软引用的对象没有被 GC
        // 一般来说，好的软引用实现在抛出 OutOfMemoryError 之前被 GC
        // 以达到内存利用的最大化
        System.out.println("get reference object after gc " + getReferObj(softReference));

        TimeUnit.SECONDS.sleep(5);
        System.exit(0);
    }

    private static <T> T getReferObj(Reference<T> reference)
    {
        if (reference != null)
        {
            return reference.get();
        }
        return null;
    }

    private static class ReferLoader<T> implements Runnable
    {
        private final ReferenceQueue<T> referenceQueue;

        public ReferLoader(ReferenceQueue<T> referenceQueue)
        {
            this.referenceQueue = referenceQueue;
        }

        public void run()
        {
            try
            {
                // 监视 referenceQueue
                // 对象没有被回收， referenceQueue 中没有对象
                while (true)
                {
                    Reference<? extends T> reference = referenceQueue.remove();
                    System.out.println("reference queue coming in !");
                    System.out.println("from reference queue " + reference.get());
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("refer loader interrupted.");
            }
        }
    }
}
