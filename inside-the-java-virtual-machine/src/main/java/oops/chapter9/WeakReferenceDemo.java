/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
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
public class WeakReferenceDemo
{
    // output:
    // now initalizing ReferObj{id=0}
    // get reference object before gc ReferObj{id=0}
    // reference queue coming in !
    // now finalizing ReferObj{id=0}
    // from reference queue null
    // get reference object after gc null
    public static void main(String[] args) throws InterruptedException
    {
        ReferenceQueue<ReferObj> referenceQueue = new ReferenceQueue<ReferObj>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ReferLoader<ReferObj>(referenceQueue));
        executorService.shutdown();

        WeakReference<ReferObj> weakReference = new WeakReference<ReferObj>(new ReferObj(), referenceQueue);

        // get reference object before gc ReferObj{id=0}
        // GC 前，弱引用对象存在
        System.out.println("get reference object before gc " + getReferObj(weakReference));

        TimeUnit.SECONDS.sleep(2);
        // 在 GC 过程中
        // 垃圾收集器调用 Reference 的 clear() 方法
        System.gc();
        TimeUnit.MICROSECONDS.sleep(1);

        // 弱引用的对象一定被回收
        // get reference object after gc null
        // 弱引用对象已被回收
        System.out.println("get reference object after gc " + getReferObj(weakReference));

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
                while (true)
                {
                    // 当弱引用对象离开弱引用状态时(即被销毁时)
                    // 或者被复活时
                    // 队列中出现该引用
                    // 垃圾收集器 实际上调用了 Reference 的 enqueue()
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
