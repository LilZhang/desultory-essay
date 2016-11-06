/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-01
 * Project        : desultory-essay
 * File Name      : App25.java
 */
public class App25
{
    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Switch aSwitch = new Switch();
        executorService.execute(new TurnOnTask(aSwitch));
        executorService.execute(new TurnOffTask(aSwitch));
        executorService.shutdown();

        TimeUnit.SECONDS.sleep(15);
        executorService.shutdownNow();
    }

    private static class Switch
    {
        private boolean swh = false;

        public synchronized void turnOff()
        {
            swh = false;
            notifyAll();
        }

        public synchronized void turnOn()
        {
            swh = true;
            notifyAll();
        }

        public synchronized void waitForOn() throws InterruptedException
        {
            if (swh == false)
            {
                wait();
            }
        }

        public synchronized void waitForOff() throws InterruptedException
        {
            if (swh == true)
            {
                wait();
            }
        }
    }

    private static class TurnOnTask implements Runnable
    {
        private Switch aSwitch;

        public TurnOnTask(Switch aSwitch)
        {
            this.aSwitch = aSwitch;
        }

        public void run()
        {

            try
            {
                while (!Thread.interrupted())
                {
                    aSwitch.waitForOff();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Turn on !");
                    aSwitch.turnOn();
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("turn on task interrupted");
            }
        }
    }

    private static class TurnOffTask implements Runnable
    {
        private Switch aSwitch;

        public TurnOffTask(Switch aSwitch)
        {
            this.aSwitch = aSwitch;
        }

        public void run()
        {
            try
            {
                while (!Thread.interrupted())
                {
                    aSwitch.waitForOn();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Turn off !");
                    aSwitch.turnOff();
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("turn off task interrupted");
            }
        }
    }
}
