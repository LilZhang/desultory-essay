/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-30
 * Project        : desultory-essay
 * File Name      : WaxOMatic.java
 */

class Switch
{
    private boolean isOn = false;

    public synchronized void turnOn()
    {
        isOn = true;
        notifyAll();
    }

    public synchronized void turnOff()
    {
        isOn = false;
        notifyAll();
    }

    public synchronized void waitForTurningOn() throws InterruptedException
    {
        while (isOn == false)
        {
            wait();
        }
    }

    public synchronized void waitForTurningOff() throws InterruptedException
    {
        while (isOn == true)
        {
            wait();
        }
    }
}

class TurnOnTask implements Runnable
{
    private Switch swh;

    public TurnOnTask(Switch swh)
    {
        this.swh = swh;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                System.out.println("Turn On !");
                TimeUnit.SECONDS.sleep(1);
                swh.turnOn();
                swh.waitForTurningOff();
            }
        }
        catch (InterruptedException e)

        {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Turn On Task");
    }
}

class TurnOffTask implements Runnable
{
    private Switch swh;

    public TurnOffTask(Switch swh)
    {
        this.swh = swh;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                swh.waitForTurningOn();
                System.out.println("Turn Off !");
                TimeUnit.SECONDS.sleep(1);
                swh.turnOff();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Turn Off Task");
    }
}

public class PlayingSwitch
{
    public static void main(String[] args)
    {
        try
        {
            Switch swh = new Switch();
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new TurnOffTask(swh));
            executorService.execute(new TurnOnTask(swh));
            TimeUnit.SECONDS.sleep(10);
            executorService.shutdownNow();

            /*
            Turn On !
            Turn Off !
            Turn On !
            Turn Off !
            Turn On !
            Turn Off !
            Turn On !
            Turn Off !
            Turn On !
            Turn Off !
            Exiting via interrupt
            Exiting via interrupt
            Ending Turn Off Task
            Ending Turn On Task
            */

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
