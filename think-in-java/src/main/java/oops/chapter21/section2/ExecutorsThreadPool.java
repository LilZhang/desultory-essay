/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section2;

import oops.chapter21.section1.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-20
 * Project        : desultory-essay
 * File Name      : CachedThreadPool.java
 */
public class ExecutorsThreadPool
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
        {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
        // shutdown 以后新的任务无法再被提交。
        // 旧任务继续运行

        ExecutorService executorService2 = Executors.newFixedThreadPool(5); // 固定数量
        for (int i = 0; i < 5; i++)
        {
            executorService2.execute(new LiftOff());
        }
        executorService2.shutdown();

        ExecutorService executorService3 = Executors.newSingleThreadExecutor(); // 排队
        for (int i = 0; i < 5; i++)
        {
            executorService3.execute(new LiftOff());
        }
        executorService3.shutdown();
    }
}
