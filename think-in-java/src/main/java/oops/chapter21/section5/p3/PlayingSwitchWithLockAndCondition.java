/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-01
 * Project        : desultory-essay
 * File Name      : WaxOMatic2.java
 */

class Switch
{
    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private boolean isOn = false;

    public void turnOn()
    {
        lock.lock();
        try
        {
            isOn = true;
            condition.signalAll();
        }
        finally
        {
            lock.unlock();
        }
    }

    public void turnOff()
    {
        lock.lock();
        try
        {
            isOn = false;
            condition.signalAll();
        }
        finally
        {
            lock.unlock();
        }
    }

    public void waitForTurningOn() throws InterruptedException
    {
        lock.lock();
        try
        {
            while (isOn == false)
            {
                condition.await();
            }
        }
        finally
        {
            lock.unlock();
        }
    }

    public void waitForTurningOff() throws InterruptedException
    {
        lock.lock();
        try
        {
            while (isOn == true)
            {
                condition.await();
            }
        }
        finally
        {
            lock.unlock();
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
                TimeUnit.MILLISECONDS.sleep(200);
                swh.turnOn();
                swh.waitForTurningOff();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("exiting via interrupt");
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
                TimeUnit.MILLISECONDS.sleep(200);
                swh.turnOff();
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("exiting via interrupt");
        }
        System.out.println("Ending Turn Off Task");
    }
}

public class PlayingSwitchWithLockAndCondition
{
    public static void main(String[] args) throws Exception
    {
        Switch swh = new Switch();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new TurnOffTask(swh));
        executorService.execute(new TurnOnTask(swh));
        TimeUnit.SECONDS.sleep(5);
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
        Turn On !
        Turn Off !
        Turn On !
        exiting via interrupt
        exiting via interrupt
        Ending Turn Off Task
        Ending Turn On Task
        */
    }
}
