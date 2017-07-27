/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-05
 * Project        : desultory-essay
 * File Name      : App.java
 */
public class App
{
    public static void main(String[] args) throws Exception
    {
        ExecutorService executorService = Executors.newCachedThreadPool();

        FutureTask task1 = new FutureTask<>(() ->
        {
            System.out.println("start task1");
            TimeUnit.SECONDS.sleep(3);
            return "3 sec from task1";
        });

        FutureTask task2 = new FutureTask<>(() ->
        {
            System.out.println("start task2");
            TimeUnit.SECONDS.sleep(3);
            return "3 sec from task2";
        });

        FutureTask task3 = new FutureTask<>(() ->
        {
            System.out.println("start task3");
            TimeUnit.SECONDS.sleep(3);
            return "3 sec from task3";
        });

        executorService.execute(task1);
        executorService.execute(task2);
        executorService.execute(task3);

        System.out.println(task1.get());
        System.out.println(task2.get());
        System.out.println(task3.get());
    }
}
