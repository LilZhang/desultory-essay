/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
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
public class PhantomReferenceDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        ReferenceQueue<ReferObj> referenceQueue = new ReferenceQueue<ReferObj>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ReferLoader<ReferObj>(referenceQueue));
        executorService.shutdown();

        PhantomReference<ReferObj> phantomReference = new PhantomReference<ReferObj>(new ReferObj(), referenceQueue);

        System.out.println("get reference object before gc " + getReferObj(phantomReference));

        TimeUnit.SECONDS.sleep(2);
        System.gc();
        TimeUnit.MICROSECONDS.sleep(1);



        System.out.println("get reference object after gc " + getReferObj(phantomReference));

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
