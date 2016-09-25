/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-02
 * Project        : desultory-essay
 * File Name      : ToastOMatic.java
 */
class Toast
{
    public enum Status
    {
        DRY,
        BUTTERED,
        JAMMED
    }

    private Status status = Status.DRY;

    private final int id;

    public Toast(int id)
    {
        this.id = id;
    }

    public void butter()
    {
        status = Status.BUTTERED;
    }

    public void jam()
    {
        status = Status.JAMMED;
    }

    public Status getStatus()
    {
        return status;
    }

    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Toast{");
        sb.append("id=").append(id);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}

class Toaster implements Runnable
{
    private LinkedBlockingQueue<Toast> toastQueue;

    private int count = 0;

    private Random random = new Random(47);

    public Toaster(LinkedBlockingQueue<Toast> toastQueue)
    {
        this.toastQueue = toastQueue;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                Toast toast = new Toast(++count);
                System.out.println(toast);
                toastQueue.put(toast);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");
    }
}

class Butterer implements Runnable
{
    private LinkedBlockingQueue<Toast> dryQueue, butteredQueue;

    public Butterer(LinkedBlockingQueue<Toast> dryQueue, LinkedBlockingQueue<Toast> butteredQueue)
    {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                // blocks until next piece of toast is available
                Toast toast = dryQueue.take();
                toast.butter();
                System.out.println(toast);
                butteredQueue.put(toast);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}

class Jammer implements Runnable
{
    private LinkedBlockingQueue<Toast> butteredQueue, finishedQueue;

    public Jammer(LinkedBlockingQueue<Toast> butteredQueue, LinkedBlockingQueue<Toast> finishedQueue)
    {
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                // blocks until next piece of toast is available
                Toast toast = butteredQueue.take();
                toast.jam();
                System.out.println(toast);
                finishedQueue.put(toast);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}

class Eater implements Runnable
{
    private LinkedBlockingQueue<Toast> finishedQueue;

    private int count = 0;

    public Eater(LinkedBlockingQueue<Toast> finishedQueue)
    {
        this.finishedQueue = finishedQueue;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                // blocks until next piece of toast is available
                Toast toast = finishedQueue.take();

                // check the toast
                if (toast.getId() != ++count ||
                        toast.getStatus() != Toast.Status.JAMMED)
                {
                    System.out.println("Error!" + toast);
                    System.exit(1);
                }
                else
                {
                    System.out.println("Chomp! " + toast);
                    System.out.println();
                }
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}

public class ToastOMatic
{
    public static void main(String[] args) throws Exception
    {
        LinkedBlockingQueue<Toast> dryQueue = new LinkedBlockingQueue<Toast>();
        LinkedBlockingQueue<Toast> butteredQueue = new LinkedBlockingQueue<Toast>();
        LinkedBlockingQueue<Toast> finishedQueue = new LinkedBlockingQueue<Toast>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Toaster(dryQueue));
        executorService.execute(new Butterer(dryQueue, butteredQueue));
        executorService.execute(new Jammer(butteredQueue, finishedQueue));
        executorService.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();

        /*
        Toast{id=1, status=DRY}
        Toast{id=1, status=BUTTERED}
        Toast{id=1, status=JAMMED}
        Chomp! Toast{id=1, status=JAMMED}

        Toast{id=2, status=DRY}
        Toast{id=2, status=BUTTERED}
        Toast{id=2, status=JAMMED}
        Chomp! Toast{id=2, status=JAMMED}

        Toast{id=3, status=DRY}
        Toast{id=3, status=BUTTERED}
        Toast{id=3, status=JAMMED}
        Chomp! Toast{id=3, status=JAMMED}

        Toast{id=4, status=DRY}
        Toast{id=4, status=BUTTERED}
        Toast{id=4, status=JAMMED}
        Chomp! Toast{id=4, status=JAMMED}

        Toast{id=5, status=DRY}
        Toast{id=5, status=BUTTERED}
        Toast{id=5, status=JAMMED}
        Chomp! Toast{id=5, status=JAMMED}

        Toast{id=6, status=DRY}
        Toast{id=6, status=BUTTERED}
        Toast{id=6, status=JAMMED}
        Chomp! Toast{id=6, status=JAMMED}

        Toast{id=7, status=DRY}
        Toast{id=7, status=BUTTERED}
        Toast{id=7, status=JAMMED}
        Chomp! Toast{id=7, status=JAMMED}

        Toast{id=8, status=DRY}
        Toast{id=8, status=BUTTERED}
        Toast{id=8, status=JAMMED}
        Chomp! Toast{id=8, status=JAMMED}

        Toast{id=9, status=DRY}
        Toast{id=9, status=BUTTERED}
        Toast{id=9, status=JAMMED}
        Chomp! Toast{id=9, status=JAMMED}

        Toast{id=10, status=DRY}
        Toast{id=10, status=BUTTERED}
        Toast{id=10, status=JAMMED}
        Chomp! Toast{id=10, status=JAMMED}

        Toast{id=11, status=DRY}
        Toast{id=11, status=BUTTERED}
        Toast{id=11, status=JAMMED}
        Chomp! Toast{id=11, status=JAMMED}

        Toast{id=12, status=DRY}
        Toast{id=12, status=BUTTERED}
        Toast{id=12, status=JAMMED}
        Chomp! Toast{id=12, status=JAMMED}

        Toast{id=13, status=DRY}
        Toast{id=13, status=BUTTERED}
        Toast{id=13, status=JAMMED}
        Chomp! Toast{id=13, status=JAMMED}

        Toast{id=14, status=DRY}
        Toast{id=14, status=BUTTERED}
        Toast{id=14, status=JAMMED}
        Chomp! Toast{id=14, status=JAMMED}

        Toast{id=15, status=DRY}
        Toast{id=15, status=BUTTERED}
        Toast{id=15, status=JAMMED}
        Chomp! Toast{id=15, status=JAMMED}

        Toaster interrupted
        Jammer interrupted
        Eater interrupted
        Butterer interrupted
        Eater off
        Jammer off
        Toaster off
        Butterer off
        */
    }
}
