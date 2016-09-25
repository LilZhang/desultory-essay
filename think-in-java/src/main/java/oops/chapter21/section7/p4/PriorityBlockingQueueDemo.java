/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p4;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-05
 * Project        : desultory-essay
 * File Name      : PriorityBlockingQueue.java
 */
class PrioritizedTask implements Runnable, Comparable<PrioritizedTask>
{
    private Random random = new Random();

    private static int counter = 0;

    private final int id = ++counter;

    private final int priority;

    protected static List<PrioritizedTask> sequence = new ArrayList<PrioritizedTask>();

    public PrioritizedTask(int priority)
    {
        this.priority = priority;
        sequence.add(this);
    }

    public int compareTo(PrioritizedTask o)
    {
        int result = 0;

        if (o != null)
        {
            result = priority - o.priority;
        }

        return result;
    }

    public void run()
    {
        try
        {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        }
        catch (InterruptedException e)
        {
            System.out.println("exiting via interrupt");
        }
        System.out.println(this);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PrioritizedTask{");
        sb.append("id=").append(id);
        sb.append(", priority=").append(priority);
        sb.append('}');
        return sb.toString();
    }

    public static class EndSentinel extends PrioritizedTask
    {
        private ExecutorService executorService;

        public EndSentinel(ExecutorService executorService)
        {
            super(-1);  // lowest priority
            this.executorService = executorService;
        }

        @Override
        public void run()
        {
            int count = 0;

            for (PrioritizedTask task : sequence)
            {
                System.out.println(task);
                if (++count % 5 == 0)
                {
                    System.out.println();
                }
                System.out.println();
                System.out.println(this + " Calling shutdownNow()");
                executorService.shutdownNow();
            }
        }
    }
}

class PrioritizedTaskProducer implements Runnable
{
    private Random random = new Random();

    private Queue<Runnable> queue;

    private ExecutorService executorService;

    public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService executorService)
    {
        this.queue = queue;
        this.executorService = executorService;
    }

    public void run()
    {
        for (int i = 0; i < 20; i++)
        {
            queue.add(new PrioritizedTask(random.nextInt(10)));
            Thread.yield();
        }
        try
        {
            for (int i = 0; i < 10; i++)
            {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }

            for (int i = 0; i < 10; i++)
            {
                queue.add(new PrioritizedTask(i));
            }
            queue.add(new PrioritizedTask.EndSentinel(executorService));
        }
        catch (InterruptedException e)
        {
            System.out.println("exiting via interrupt");
        }
        System.out.println("Finished PrioritizedTaskProducer");
    }
}

class PrioritizedTaskConsumer implements Runnable
{
    private PriorityBlockingQueue<Runnable> queue;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> queue)
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
    }
}

public class PriorityBlockingQueueDemo
{
    public static void main(String[] args)
    {
//        Random random = new Random();
        ExecutorService executorService = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
        executorService.execute(new PrioritizedTaskProducer(queue, executorService));
        executorService.execute(new PrioritizedTaskConsumer(queue));
    }
}
