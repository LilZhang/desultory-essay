/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-09-01
 * Project        : desultory-essay
 * File Name      : PlayingSwitch2.java
 */
class Switch
{
    private boolean on = false;

    public synchronized void turnOn()
    {
        on = true;
        System.out.println("ON !");
        notifyAll();
    }

    public synchronized void prod()
    {
        if (on)
        {
            turnOff();
        }
        else
        {
            turnOn();
        }
    }

    public synchronized void turnOff()
    {
        on = false;
        System.out.println("OFF !");
        notifyAll();
    }

    public synchronized void waitForTurningOn() throws InterruptedException
    {
        while (on == false)
        {
            wait();
            System.out.println("turned on.");
        }
    }

    public synchronized void waitForTurningOff() throws InterruptedException
    {
        // 若已打开，则挂起
        // (关闭时会被唤醒) 执行打印语句
        while (on == true)
        {
            wait();
            System.out.println("turned off.");
        }
    }
}

public class PlayingSwitch2
{
    public static void main(String[] args)
    {
        final Switch aSwitch = new Switch();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable()
        {
            public void run()
            {
                try
                {
                    while (!Thread.interrupted())
                    {
                        aSwitch.waitForTurningOff();
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("exiting via interrupt");
                }
            }
        });
        executorService.execute(new Runnable()
        {
            public void run()
            {
                try
                {
                    while (!Thread.interrupted())
                    {
                        aSwitch.waitForTurningOn();
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("exiting via interrupt");
                }
            }
        });

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        try
        {
            while ((s = bufferedReader.readLine()) != null)
            {
                if ("exit".equals(s))
                {
                    executorService.shutdownNow();
                    break;
                }
                else
                {
                    aSwitch.prod();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (Exception e)
                {
                    // ignore
                }
            }
        }
    }
}
