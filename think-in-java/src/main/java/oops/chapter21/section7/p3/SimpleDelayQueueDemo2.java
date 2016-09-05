/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-05
 * Project        : desultory-essay
 * File Name      : SimpleDelayQueueDemo.java
 */
public class SimpleDelayQueueDemo2
{
    public static void main(String[] args)
    {
        List<DeTask2> deTaskList = new ArrayList<DeTask2>();
        deTaskList.add(new DeTask2(1, 800));     // 第一个被 take()
        deTaskList.add(new DeTask2(2, 1800));    // 第二个被 take()
        deTaskList.add(new DeTask2(3, 2800));
        deTaskList.add(new DeTask2(4, 3800));
        deTaskList.add(new DeTask2(5, 4800));
        deTaskList.add(new DeTask2(6, 2000));
        deTaskList.add(new DeTask2(7, 1900));    // 第三个被 take()
        deTaskList.add(new DeTask2(8, 2100));

        /*
        DeTask2{id=1, delay=800}
        DeTask2{id=2, delay=1800}
        DeTask2{id=7, delay=1900}
        DeTask2{id=6, delay=2000}
        DeTask2{id=8, delay=2100}
        DeTask2{id=3, delay=2800}
        DeTask2{id=4, delay=3800}
        DeTask2{id=5, delay=4800}
        */

        final DelayQueue<DeTask2> deTaskDelayQueue = new DelayQueue<DeTask2>(deTaskList);
        final ExecutorService executorService = Executors.newCachedThreadPool();

        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
//                    long timeMillis = System.currentTimeMillis();
                    while (deTaskDelayQueue.size() > 0)
                    {
                        DeTask2 deTask2 = deTaskDelayQueue.take();
                        executorService.execute(deTask2);
//                        System.out.println(" time: " + (System.currentTimeMillis() - timeMillis));
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("exiting via interrupt");
                }
            }
        }.start();
    }
}

class DeTask2 implements Delayed, Runnable
{
    private final long delayNano;

    private long delay;

    private final int id;

    public DeTask2(int id, int delay)
    {
        this.id = id;
        this.delay = delay;
        this.delayNano = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delay, TimeUnit.MILLISECONDS);
    }

    public long getDelay(TimeUnit unit)
    {
        return unit.convert(delayNano - System.nanoTime(), TimeUnit.NANOSECONDS);
//        return delay;
//        return TimeUnit.NANOSECONDS.convert(delay, TimeUnit.MILLISECONDS);
    }

    public int compareTo(Delayed o)
    {
        int result = 0;
        if (o != null)
        {
            if (o instanceof DeTask2)
            {
                if (delayNano > ((DeTask2) o).delayNano)
                {
                    result = 1;
                }
                else if (delayNano < ((DeTask2) o).delayNano)
                {
                    result = -1;
                }
            }
            else
            {
                throw new RuntimeException("error");
            }
        }

        return result;
    }

    public void run()
    {
        System.out.println(this);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("DeTask2{");
        sb.append("id=").append(id);
        sb.append(", delay=").append(delay);
        sb.append('}');
        return sb.toString();
    }
}
