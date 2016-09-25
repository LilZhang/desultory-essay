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
 * Create Author  : lilzhang
 * Create Date    : 2016-08-20
 * Project        : desultory-essay
 * File Name      : SimpleDaemons.java
 */
public class SimpleDaemons implements Runnable
{
    public void run()
    {
        try
        {
            while (true)
            {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + ": " + this);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 后台线程的 finally 不被执行
            // 后台线程里创建的子线程仍为后台线程
        }
    }

    public static void main(String[] args) throws Exception
    {
        for (int i = 0; i < 10; i++)
        {
            Thread thread = new Thread(new SimpleDaemons());
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("All daemon started.");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
