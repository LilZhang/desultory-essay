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
 * Create Author  : lilzhang
 * Create Date    : 2016-09-01
 * Project        : desultory-essay
 * File Name      : TestWait1.java
 */
class Printer
{
    public synchronized void waitForPrint() throws InterruptedException
    {

        while (!Thread.interrupted())
        {
//            System.out.println("before wait()");
            wait();
            System.out.println("after wait(): " + Thread.currentThread() + " ");
        }
    }

    public synchronized void goForIt()
    {
        notify();
    }
}

public class TestWait1
{
    public static void main(String[] args)
    {
        System.out.println("输入任意字符串+回车来随机唤醒某个线程(回车退出)");
        final Printer printer = new Printer();

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++)
        {
            executorService.execute(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        printer.waitForPrint();
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("exiting via interrupt");
                    }
                }
            });
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        try
        {
            while ((s = bufferedReader.readLine()) != null)
            {
                if (s.length() > 0)
                {
                    System.out.println("good");
                    printer.goForIt();
                }
                else
                {
                    executorService.shutdownNow();
                    break;
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
