/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-05
 * Project        : desultory-essay
 * File Name      : SimpleDelayQueueDemo.java
 */
public class SimpleDelayQueueDemo
{
    public static void main(String[] args)
    {
        List<DeTask> deTaskList = new ArrayList<DeTask>();
        deTaskList.add(new DeTask(1, 800));     // 第一个被 take()
        deTaskList.add(new DeTask(2, 1800));    // 第二个被 take()
        deTaskList.add(new DeTask(3, 2800));
        deTaskList.add(new DeTask(4, 3800));
        deTaskList.add(new DeTask(5, 4800));
        deTaskList.add(new DeTask(6, 2000));
        deTaskList.add(new DeTask(7, 1900));    // 第三个被 take()
        deTaskList.add(new DeTask(8, 2100));

        /*output:
        DeTask{id=1, delay=800} time: 795
        DeTask{id=2, delay=1800} time: 1796
        DeTask{id=7, delay=1900} time: 1896
        DeTask{id=6, delay=2000} time: 1996
        DeTask{id=8, delay=2100} time: 2096
        DeTask{id=3, delay=2800} time: 2796
        DeTask{id=4, delay=3800} time: 3796
        DeTask{id=5, delay=4800} time: 4796
        */

        final DelayQueue<DeTask> deTaskDelayQueue = new DelayQueue<DeTask>(deTaskList);

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    long timeMillis = System.currentTimeMillis();
                    while (deTaskDelayQueue.size() > 0)
                    {
                        DeTask deTask = deTaskDelayQueue.take();
                        System.out.println(deTask + " time: " + (System.currentTimeMillis() - timeMillis));
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("exiting via interrupt");
                }
            }
        };
        thread.start();

        try
        {
            TimeUnit.SECONDS.sleep(10);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}

class DeTask implements Delayed
{
    private final long delayNano;

    private long delay;

    private final int id;

    public DeTask(int id, int delay)
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
            if (o instanceof DeTask)
            {
                if (delayNano > ((DeTask) o).delayNano)
                {
                    result = 1;
                }
                else if (delayNano < ((DeTask) o).delayNano)
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

    /*public void run()
    {
        System.out.println(this);
    }*/

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("DeTask{");
        sb.append("id=").append(id);
        sb.append(", delay=").append(delay);
        sb.append('}');
        return sb.toString();
    }
}
