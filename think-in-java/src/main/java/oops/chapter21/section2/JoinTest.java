/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section2;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-08-20
 * Project        : desultory-essay
 * File Name      : JoinTest.java
 */
public class JoinTest
{
    public static void main(String[] args)
    {
        Sleeper sleeper1 = new Sleeper("sleep1", 1500);
        Sleeper sleeper2 = new Sleeper("sleep2", 1500);

        Joiner joiner1 = new Joiner("joiner1", sleeper1);
        Joiner joiner2 = new Joiner("joiner2", sleeper2);

        sleeper2.interrupt();

        /*sleep2 sleep start.
        sleep1 sleep start.
        sleep2[sleeper] was interrupted. isInterrupted(): false // 线程抛出interrupted异常时会重置isInterrupted()
        sleep2 sleep complete.
        joiner1 joiner start.
        joiner2 joiner start.
        joiner2 joiner complete.
        sleep1 sleep complete.
        joiner1 joiner complete.*/
    }
}

class Sleeper extends Thread
{
    private int duration;

    public Sleeper(String name, int duration)
    {
        super(name);
        this.duration = duration;
        this.start();
    }

    @Override
    public void run()
    {
        System.out.println(this.getName() + " sleep start.");
        try
        {
            TimeUnit.MILLISECONDS.sleep(duration);
        }
        catch (Exception e)
        {
            System.out.println(this.getName() +
                    "[sleeper] was interrupted. isInterrupted(): " +
                    this.isInterrupted());
        }
        System.out.println(this.getName() + " sleep complete.");
    }
}

class Joiner extends Thread
{
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper)
    {
        super(name);
        this.sleeper = sleeper;
        this.start();
    }

    @Override
    public void run()
    {
        System.out.println(this.getName() + " joiner start.");
        try
        {
            sleeper.join();
        }
        catch (Exception e)
        {
            System.out.println(this.getName() +
                    "[joiner] was interrupted. isInterrupted(): " +
                    this.isInterrupted());
        }
        System.out.println(this.getName() + " joiner complete.");
    }
}

