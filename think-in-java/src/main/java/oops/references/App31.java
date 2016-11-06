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
 * File Name      : App31.java
 */
public class App31
{
    public static void main(String[] args)
    {
        final BlockingQueue<DelayObj> delayObjBlockingQueue = new DelayQueue<DelayObj>();

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
                        while (monitor.isBool())
                        {
                            monitor.wait();
                        }

                        System.out.println(name + " get lock and put obj.");

                        delayObjBlockingQueue.put(new DelayObj(4, TimeUnit.SECONDS));
                        delayObjBlockingQueue.put(new DelayObj(10000, TimeUnit.MILLISECONDS));
                        delayObjBlockingQueue.put(new DelayObj(7, TimeUnit.SECONDS));
                        delayObjBlockingQueue.put(new DelayObj(12000, TimeUnit.MILLISECONDS));
                        delayObjBlockingQueue.put(new DelayObj(15, TimeUnit.SECONDS));

                        System.out.println(name + " put ok.");
                        monitor.setBool(true);
                        monitor.notify();
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.format("%s interrupted", name);
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
                        while (!monitor.isBool())
                        {
                            monitor.wait();
                        }

                        long timeMillis = System.currentTimeMillis();
                        System.out.println(name + " get lock and take obj out.");

                        int size = delayObjBlockingQueue.size();
                        for (int i = 0; i < size; i++)
                        {
                            System.out.println(delayObjBlockingQueue.take() + " time spend: " + (System.currentTimeMillis() - timeMillis));
                        }

                        System.out.println(name + " take ok.");
                    }

                    executorService.shutdownNow();
                }
                catch (InterruptedException e)
                {
                    System.out.format("%s interrupted", name);
                }
            }
        });
    }

    private static class DelayObj implements Delayed
    {
        private long delayTime;

        private long delayPoint;

        private TimeUnit timeUnit;

        public DelayObj(int delayTime, TimeUnit timeUnit)
        {
            long currentNano = System.nanoTime();
            this.delayTime = delayTime;
            this.delayPoint = delayTime + timeUnit.convert(currentNano, TimeUnit.NANOSECONDS);
            this.timeUnit = timeUnit;
        }


        // getDelay() 会返回 延迟时间还有多久，或者过去了多久(负值)
        // 所以一定是动态的
        // 而且和当前时间密切相关的
        public long getDelay(TimeUnit unit)
        {
            return unit.convert(this.delayPoint - timeUnit.convert(System.nanoTime(), TimeUnit.NANOSECONDS), this.timeUnit);
        }

        public int compareTo(Delayed o)
        {
            if (o != null)
            {
                if (this.delayPoint > ((DelayObj) o).delayPoint)
                {
                    return 1;
                }
                else if (this.delayPoint < ((DelayObj) o).delayPoint)
                {
                    return -1;
                }
            }
            return 0;
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("DelayObj{");
            sb.append("delayTime=").append(delayTime);
            sb.append(", timeUnit=").append(timeUnit);
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
