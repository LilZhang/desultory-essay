/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.concurrent.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-03
 * Project        : desultory-essay
 * File Name      : App33.java
 */
public class App33
{
    public static void main(String[] args)
    {
        final BlockingQueue<PriorityObj> priorityObjBlockingQueue = new PriorityBlockingQueue<PriorityObj>();

        final Monitor monitor = new Monitor();

        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable()
        {
            public void run()
            {
                String name = Thread.currentThread().getName();
                try
                {
                    synchronized (monitor)
                    {
                        while (!monitor.isBool())
                        {
                            monitor.wait();
                        }

                        System.out.println(name + " get lock and take obj out.");

                        int size = priorityObjBlockingQueue.size();
                        for (int i = 0; i < size; i++)
                        {
                            PriorityObj take = priorityObjBlockingQueue.take();
                            System.out.println(take);
                            TimeUnit.SECONDS.sleep(2);
                        }

                        System.out.println(name + " take ok.");
                    }
                    executorService.shutdownNow();
                }
                catch (InterruptedException e)
                {
                    System.out.println(name + " interrupted");
                }
            }
        });

        executorService.execute(new Runnable()
        {
            public void run()
            {
                String name = Thread.currentThread().getName();
                try
                {
                    synchronized (monitor)
                    {
                        while (monitor.isBool())
                        {
                            monitor.wait();
                        }

                        System.out.println(name + " get lock and put obj.");

                        priorityObjBlockingQueue.put(new PriorityObj(7));
                        priorityObjBlockingQueue.put(new PriorityObj(4));
                        priorityObjBlockingQueue.put(new PriorityObj(2));
                        priorityObjBlockingQueue.put(new PriorityObj(6));
                        priorityObjBlockingQueue.put(new PriorityObj(8));
                        priorityObjBlockingQueue.put(new PriorityObj(3));
                        priorityObjBlockingQueue.put(new PriorityObj(5));

                        TimeUnit.SECONDS.sleep(2);

                        System.out.println(name + " put ok.");

                        monitor.setBool(true);
                        monitor.notifyAll();
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println(name + " interrupted");
                }
            }
        });
    }

    private static class PriorityObj implements Comparable<PriorityObj>
    {
        private int priority;

        public PriorityObj(int priority)
        {
            this.priority = priority;
        }

        public int compareTo(PriorityObj o)
        {
            if (o != null)
            {
                return this.priority - o.priority;
            }

            return 0;
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("PriorityObj{");
            sb.append("priority=").append(priority);
            sb.append('}');
            return sb.toString();
        }
    }

    private static class Monitor
    {
        private boolean bool = false;

        public boolean isBool()
        {
            return bool;
        }

        public void setBool(boolean bool)
        {
            this.bool = bool;
        }
    }
}
