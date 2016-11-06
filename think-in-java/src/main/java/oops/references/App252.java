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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-01
 * Project        : desultory-essay
 * File Name      : App252.java
 */
public class App252
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Switch aSwitch = new Switch();
        executorService.execute(new TurnOnTask(aSwitch));
        executorService.execute(new TurnOffTask(aSwitch));
        executorService.shutdown();
    }

    private static class Switch
    {
        private boolean swh = false;

        private Lock lock = new ReentrantLock();

        private Condition condition = lock.newCondition();

        public void turnOn()
        {
            lock.lock();
            try
            {
                swh = true;
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
                swh = false;
                condition.signalAll();
            }
            finally
            {
                lock.unlock();
            }
        }

        public void waitForOn() throws InterruptedException
        {
            if (swh == false)
            {
                condition.await();
            }
        }

        public void waitForOff() throws InterruptedException
        {
            if (swh == true)
            {
                condition.await();
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
                    System.out.println("turn on !");
                    aSwitch.turnOn();
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("thread 1 interrupted");
            }

            System.out.println("thread 1 terminated");
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
                    System.out.println("turn off !");
                    aSwitch.turnOff();
                }
            }
            catch (InterruptedException e)
            {
                System.out.println("thread 2 interrupted");
            }

            System.out.println("thread 2 terminated");
        }
    }
}
