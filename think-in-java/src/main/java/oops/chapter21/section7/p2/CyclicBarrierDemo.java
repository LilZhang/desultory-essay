/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-04
 * Project        : desultory-essay
 * File Name      : CyclicBarrierDemo.java
 */

class Horse implements Runnable
{
    private static int counter = 0;

    private final int id = counter++;

    private int strides = 0;

    private static Random random = new Random();

    private CyclicBarrier cyclicBarrier;

    public Horse(CyclicBarrier cyclicBarrier)
    {
        this.cyclicBarrier = cyclicBarrier;
    }

    public synchronized int getStrides()
    {
        return strides;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                synchronized (this)
                {
                    strides += random.nextInt(3);
                }
                cyclicBarrier.await();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(this + " exiting via interrupt");
        }
        catch (BrokenBarrierException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Horse{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    public String tracks()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getStrides(); i++)
        {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}

class HorseRace
{
    static final int FINISH_LINE = 45;

    private List<Horse> horses = new ArrayList<Horse>();

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private CyclicBarrier cyclicBarrier;

    public HorseRace(int nHorse, final int pause)
    {
        cyclicBarrier = new CyclicBarrier(nHorse, new Runnable()
        {
            public void run()
            {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++)
                {
                    sb.append("=");
                }
                System.out.println(sb.toString());

                for (Horse horse : horses)
                {
                    System.out.println(horse.tracks());
                }
                for (Horse horse : horses)
                {
                    if (horse.getStrides() >= FINISH_LINE)
                    {
                        System.out.println(horse + " won!");
                        executorService.shutdownNow();
                        return;
                    }
                }
                try
                {
                    TimeUnit.MILLISECONDS.sleep(pause);
                }
                catch (InterruptedException e)
                {
                    System.out.println("barrier-action sleep interrupt");
                }
            }
        });

        for (int i = 0; i < nHorse; i++)
        {
            Horse horse = new Horse(cyclicBarrier);
            horses.add(horse);
            executorService.execute(horse);
        }
    }
}

public class CyclicBarrierDemo
{
    public static void main(String[] args)
    {
        int nHorse = 7, pause = 1000;
        new HorseRace(nHorse, pause);
    }
}
