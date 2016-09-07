/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-06
 * Project        : desultory-essay
 * File Name      : SemaphoreDemo.java
 */
class CheckoutTask<T> implements Runnable
{
    private static int counter = 0;

    private final int id = ++counter;

    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool)
    {
        this.pool = pool;
    }

    public void run()
    {
        try
        {
            T item = pool.checkOut();
            System.out.println(this + " checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " checking in " + item);
            pool.checkIn(item);
        }
        catch (InterruptedException e)
        {
            System.out.println(this + " exiting via interrupt");
        }
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("CheckoutTask{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

public class SemaphoreDemo
{
    final static int SIZE = 25;

    public static void main(String[] args)
    {
        final Pool<Fat> pool = new Pool<Fat>(Fat.class, SIZE);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++)
        {
            executorService.execute(new CheckoutTask<Fat>(pool));
        }

        System.out.println("All CheckoutTasks created");

        List<Fat> list = new ArrayList<Fat>();

        try
        {
            for (int i = 0; i < SIZE; i++)
            {
                Fat fat = pool.checkOut();
                System.out.println(i + ": main() thread checked out ");
                System.out.println(fat);
                list.add(fat);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("...");
        }

        Future<?> blocked = executorService.submit(new Runnable()
        {
            public void run()
            {
                try
                {
                    pool.checkOut();
                }
                catch (InterruptedException e)
                {
                    System.out.println("checkOut() Interrupt");
                }
            }
        });

        try
        {
            TimeUnit.SECONDS.sleep(4);
        }
        catch (InterruptedException e)
        {
            System.out.println("wait interrupted");
        }
        blocked.cancel(true);

        for (Fat fat : list)
        {
            pool.checkIn(fat);
        }
        for (Fat fat : list)
        {
            pool.checkIn(fat);  // second check in ignored
        }
        executorService.shutdown();
    }
}
