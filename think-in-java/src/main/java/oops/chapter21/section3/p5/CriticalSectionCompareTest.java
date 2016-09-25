/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section3.p5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-22
 * Project        : desultory-essay
 * File Name      : CriticalSectionCompareTest.java
 */

// critical section: 临界区
// 就是这个

   /*
   synchronized(syncObject)
    {
        // this code can be accessed
        // by only one task at a time
    }
        */

public class CriticalSectionCompareTest
{
    public static class Pair  // 非线程安全
    {
        private int x, y;

        public Pair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public Pair()
        {
            this(0, 0);
        }

        public int getX()
        {
            return x;
        }

        public int getY()
        {
            return y;
        }

        public void incrementX()
        {
            x++;    // 非线程安全操作
        }

        public void incrementY()
        {
            y++;    // 非线程安全操作
        }

        public class PairValueNotEqualException extends RuntimeException
        {
            public PairValueNotEqualException()
            {
                super("Pair value not equal: " + Pair.this);
            }
        }

        public void checkState()
        {
            if (x != y)
            {
                throw new PairValueNotEqualException();
            }
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("Pair{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append('}');
            return sb.toString();
        }
    }

    public static abstract class PairManager
    {
        AtomicInteger checkCounter = new AtomicInteger(0);

        protected Pair p = new Pair();

        private List<Pair> storage = Collections.synchronizedList(
                new ArrayList<Pair>());

        public synchronized Pair getPair()
        {
            // Make a copy to keep the original safe
            return new Pair(p.getX(), p.getY());
        }

        protected void store(Pair p)
        {
            storage.add(p);
            try
            {
                TimeUnit.MILLISECONDS.sleep(50);
            }
            catch (InterruptedException e)
            {
                // ignore
            }
        }

        public abstract void increment();
    }

    public static class PairManipulator implements Runnable
    {
        private PairManager pm;

        public PairManipulator(PairManager pm)
        {
            this.pm = pm;
        }

        public void run()
        {
            while (true)
            {
                pm.increment();
            }
        }

        @Override
        public String toString()
        {
            return "Pair: " + pm.getPair() +
                    " checkCounter = " + pm.checkCounter.get();
        }
    }

    public static class PairChecker implements Runnable
    {
        private PairManager pm;

        public PairChecker(PairManager pm)
        {
            this.pm = pm;
        }

        public void run()
        {
            while (true)
            {
                pm.checkCounter.incrementAndGet();
                pm.getPair().checkState();
            }
        }
    }

    public static void MainJob(PairManager... pairManagers)
    {
        int size = pairManagers.length;
        ExecutorService executorService = Executors.newCachedThreadPool();

        PairManipulator[] pairManipulators = new PairManipulator[size];
        for (int i = 0; i < size; i++)
        {
            pairManipulators[i] = new PairManipulator(pairManagers[i]);
        }

        PairChecker[] pairCheckers = new PairChecker[size];
        for (int i = 0; i < size; i++)
        {
            pairCheckers[i] = new PairChecker(pairManagers[i]);
        }

        for (int i = 0; i < size; i++)
        {
            executorService.execute(pairManipulators[i]);
        }

        for (int i = 0; i < size; i++)
        {
            executorService.execute(pairCheckers[i]);
        }

        try
        {
            TimeUnit.MILLISECONDS.sleep(700);
        }
        catch (InterruptedException e)
        {
            System.out.println("interrupt");
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++)
        {
            sb.append("PairManipulator")
                    .append(i)
                    .append(": ")
                    .append(pairManipulators[i])
                    .append("\n");
        }
        System.out.println(sb.toString());
        System.exit(0);
    }

    public static void main(String[] args)
    {
        MainJob(new CriticalSectionCompare.PairManager1(),
                new CriticalSectionCompare.PairManager2()/*,
                new CriticalSectionCompare.ExplicitPairManager1(),
                new CriticalSectionCompare.ExplicitPairManager2()*/
        );

//        PairManipulator0: Pair: Pair{x=14, y=14} checkCounter = 1411247   （synchronized锁全方法）
//        PairManipulator1: Pair: Pair{x=15, y=15} checkCounter = 29657741  （synchronized锁临界区）
    }
}


