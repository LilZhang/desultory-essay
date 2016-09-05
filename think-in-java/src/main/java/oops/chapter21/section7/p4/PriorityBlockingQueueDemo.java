/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    /*public static class EndSentinel extends PrioritizedTask
    {

    }*/
}

public class PriorityBlockingQueueDemo
{
}
