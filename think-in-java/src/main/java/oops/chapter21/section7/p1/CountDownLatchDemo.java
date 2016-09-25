/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p1;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-04
 * Project        : desultory-essay
 * File Name      : CountDownLatchDemo.java
 */

class TaskPortion implements Runnable
{
    private static int counter = 0;

    private int id = counter++;

    private static Random random = new Random();

    private CountDownLatch countDownLatch;

    public TaskPortion(CountDownLatch countDownLatch)
    {
        this.countDownLatch = countDownLatch;
    }

    public void run()
    {
        try
        {
            doWork();
            countDownLatch.countDown();
        }
        catch (InterruptedException e)
        {
            System.out.println(this + " exiting via interrupt");
        }
    }

    private void doWork() throws InterruptedException
    {
        int timeout = random.nextInt(2000);
        TimeUnit.MILLISECONDS.sleep(timeout);
        System.out.println(this + " complete. time " + timeout);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("TaskPortion{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

class WaitingTask implements Runnable
{
    private static int counter = 0;

    private int id = counter++;

    private Random random = new Random(47);

    private CountDownLatch countDownLatch;

    public WaitingTask(CountDownLatch countDownLatch)
    {
        this.countDownLatch = countDownLatch;
    }

    public void run()
    {
        try
        {
            countDownLatch.await();
            System.out.println("Latch barrier passed for " + this);
        }
        catch (InterruptedException e)
        {
            System.out.println(this + " exiting via interrupt");
        }
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("WaitingTask{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

public class CountDownLatchDemo
{
    private static int SIZE = 30;

    public static void main(String[] args)
    {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
        {
            executorService.execute(new WaitingTask(countDownLatch));
        }
        for (int i = 0; i < SIZE; i++)
        {
            executorService.execute(new TaskPortion(countDownLatch));
        }
        executorService.shutdown();

        /*
        TaskPortion{id=25} complete. time 2
        TaskPortion{id=29} complete. time 189
        TaskPortion{id=6} complete. time 215
        TaskPortion{id=17} complete. time 220
        TaskPortion{id=26} complete. time 566
        TaskPortion{id=4} complete. time 659
        TaskPortion{id=22} complete. time 688
        TaskPortion{id=19} complete. time 705
        TaskPortion{id=9} complete. time 876
        TaskPortion{id=2} complete. time 913
        TaskPortion{id=24} complete. time 925
        TaskPortion{id=13} complete. time 937
        TaskPortion{id=21} complete. time 980
        TaskPortion{id=3} complete. time 1096
        TaskPortion{id=28} complete. time 1098
        TaskPortion{id=8} complete. time 1222
        TaskPortion{id=27} complete. time 1233
        TaskPortion{id=16} complete. time 1250
        TaskPortion{id=11} complete. time 1256
        TaskPortion{id=10} complete. time 1468
        TaskPortion{id=15} complete. time 1501
        TaskPortion{id=5} complete. time 1511
        TaskPortion{id=7} complete. time 1662
        TaskPortion{id=18} complete. time 1689
        TaskPortion{id=23} complete. time 1706
        TaskPortion{id=20} complete. time 1814
        TaskPortion{id=14} complete. time 1900
        TaskPortion{id=12} complete. time 1914
        TaskPortion{id=0} complete. time 1961
        TaskPortion{id=1} complete. time 1964
        Latch barrier passed for WaitingTask{id=2}
        Latch barrier passed for WaitingTask{id=3}
        Latch barrier passed for WaitingTask{id=1}
        Latch barrier passed for WaitingTask{id=0}
        Latch barrier passed for WaitingTask{id=4}
        */
    }
}
