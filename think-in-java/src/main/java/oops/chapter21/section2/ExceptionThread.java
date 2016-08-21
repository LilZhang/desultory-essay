/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-21
 * Project        : desultory-essay
 * File Name      : ExceptionThread.java
 */
public class ExceptionThread implements Runnable
{
    public void run()
    {
        // 抛出 RuntimeException
        // 若抛出 checked exception，将无法通过编译。
        // 需 try catch
        throw new RuntimeException("from ExceptionThread");
    }

    public static void main(String[] args)
    {
        try
        {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new ExceptionThread());
            executorService.shutdown();
        }
        catch (Exception e)
        {
            // failed to catch exception in thread
            System.out.println("exception caught");
//            e.printStackTrace();
        }
    }
}
