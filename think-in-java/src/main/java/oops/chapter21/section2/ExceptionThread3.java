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
 * File Name      : ExceptionThread3.java
 */
public class ExceptionThread3 implements Runnable
{
    public void run()
    {
        throw new RuntimeException("from ExceptionThread3");
    }

    public static void main(String[] args)
    {
        // 设置全局的默认异常处理器
        Thread.setDefaultUncaughtExceptionHandler(
                new MyUncaughtExceptionHandler2());

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExceptionThread3());
        executorService.shutdown();
    }
}

class MyUncaughtExceptionHandler2 implements Thread.UncaughtExceptionHandler
{
    public void uncaughtException(Thread t, Throwable e)
    {
        System.out.println("caught " + e);
    }
}
