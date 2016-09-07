/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-06
 * Project        : desultory-essay
 * File Name      : SimplePriorityBlockingQueueDemo.java
 */
class PriorityTask implements Comparable<PriorityTask>
{
    private final int priority;

    public PriorityTask(int priority)
    {
        this.priority = priority;
    }

    public int getPriority()
    {
        return priority;
    }

    public int compareTo(PriorityTask o)
    {
        int result = 0;
        if (o != null)
        {
            result = priority - o.priority;
        }

        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PriorityTask{");
        sb.append("priority=").append(priority);
        sb.append('}');
        return sb.toString();
    }
}

public class SimplePriorityBlockingQueueDemo
{
    public static void main(String[] args)
    {
        List<PriorityTask> priorityTaskList = new ArrayList<PriorityTask>();
        priorityTaskList.add(new PriorityTask(6));
        priorityTaskList.add(new PriorityTask(2));
        priorityTaskList.add(new PriorityTask(3));
        priorityTaskList.add(new PriorityTask(1));
        priorityTaskList.add(new PriorityTask(5));
        priorityTaskList.add(new PriorityTask(4));

        PriorityBlockingQueue queue = new PriorityBlockingQueue(priorityTaskList);
        try
        {
            while (!queue.isEmpty())
            {
                System.out.println(queue.take());
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("exiting via interrupt");
        }

        /* 默认: 数字由小到大，字母按字典序排列
        PriorityTask{priority=1}
        PriorityTask{priority=2}
        PriorityTask{priority=3}
        PriorityTask{priority=4}
        PriorityTask{priority=5}
        PriorityTask{priority=6}
        */

        System.out.println();

        PriorityBlockingQueue queue2 = new PriorityBlockingQueue(priorityTaskList.size(), new Comparator<PriorityTask>()
        {
            public int compare(PriorityTask o1, PriorityTask o2)
            {
                int result = 0;

                if (o1 != null && o2 != null)
                {
                    result = o2.getPriority() - o1.getPriority();
                }

                return result;
            }
        });

        queue2.addAll(priorityTaskList);

        try
        {
            while (!queue2.isEmpty())
            {
                System.out.println(queue2.take());
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("exiting via interrupt");
        }

        /* 由 Comparator 指定的优先级规则
        PriorityTask{priority=6}
        PriorityTask{priority=5}
        PriorityTask{priority=4}
        PriorityTask{priority=3}
        PriorityTask{priority=2}
        PriorityTask{priority=1}
        */

        try
        {
            TimeUnit.SECONDS.sleep(10);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
