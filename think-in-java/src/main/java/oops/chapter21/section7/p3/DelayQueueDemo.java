/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-05
 * Project        : desultory-essay
 * File Name      : DelayQueueDemo.java
 */
class DelayedTask implements Runnable, Delayed
{
    private static int counter = 0;

    private final int id = ++counter;

    private final int delta;

    private final long trigger;

    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

    public DelayedTask(int delayInMilliseconds)
    {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() +
                TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }

    public long getDelay(TimeUnit unit)
    {
        return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    public int compareTo(Delayed o)
    {
        DelayedTask task = (DelayedTask) o;
        int result = 0;
        if (trigger < task.trigger)
        {
            result = -1;
        }
        else if (trigger > task.trigger)
        {
            result = 1;
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
        final StringBuilder sb = new StringBuilder("DelayedTask{");
        sb.append("id=").append(id);
        sb.append(", delta=").append(delta);
        sb.append('}');
        return sb.toString();
    }

    public static class EndSentinel extends DelayedTask
    {
        private ExecutorService executorService;

        public EndSentinel(int delayInMilliseconds, ExecutorService executorService)
        {
            super(delayInMilliseconds);
            this.executorService = executorService;
        }

        @Override
        public void run()
        {
            for (DelayedTask task : sequence)
            {
                System.out.println(task + " ");
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow()");
            executorService.shutdownNow();
        }
    }
}

class DelayedTaskConsumer implements Runnable
{
    private DelayQueue<DelayedTask> queue;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> queue)
    {
        this.queue = queue;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                queue.take().run();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("exiting via interrupt");
        }
        System.out.println("finished DelayedTaskConsumer");
    }
}

public class DelayQueueDemo
{
    public static void main(String[] args)
    {
        Random random = new Random();
        ExecutorService executorService = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
        for (int i = 0; i < 20; i++)
        {
            queue.put(new DelayedTask(random.nextInt(5000)));
        }
        queue.add(new DelayedTask.EndSentinel(5000, executorService));

        executorService.execute(new DelayedTaskConsumer(queue));
    }
}
