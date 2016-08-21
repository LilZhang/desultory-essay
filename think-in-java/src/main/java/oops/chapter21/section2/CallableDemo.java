/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-20
 * Project        : desultory-essay
 * File Name      : CallableDemo.java
 */
public class CallableDemo
{
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> list = new ArrayList<Future<String>>(10);
        for (int i = 0; i < 10; i++)
        {
            Future<String> future = executorService.submit(new TaskWithResult());
            list.add(future);
        }

        long millis = System.currentTimeMillis();
        for (Future<String> future : list)
        {
            try
            {
                String s = future.get();    // block when getting value
                System.out.println(s + " time: " + (System.currentTimeMillis() - millis));

                /*call(): TaskWithResult{id=1} time: 1002
                call(): TaskWithResult{id=2} time: 2001
                call(): TaskWithResult{id=3} time: 3001
                call(): TaskWithResult{id=4} time: 4001
                call(): TaskWithResult{id=5} time: 5002
                call(): TaskWithResult{id=6} time: 6002
                call(): TaskWithResult{id=7} time: 7002
                call(): TaskWithResult{id=8} time: 8003
                call(): TaskWithResult{id=9} time: 9002
                call(): TaskWithResult{id=10} time: 10002*/
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
class TaskWithResult implements Callable<String>
{
    private static int counter = 0;

    private int id = ++counter;

    public String call() throws Exception
    {
        Thread.sleep(id * 1000);
        return "call(): " + toString();
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("TaskWithResult{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
