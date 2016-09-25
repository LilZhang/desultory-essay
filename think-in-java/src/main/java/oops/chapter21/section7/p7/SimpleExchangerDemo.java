/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-09-07
 * Project        : desultory-essay
 * File Name      : SimpleExchangerDemo.java
 */
public class SimpleExchangerDemo
{
    public static void main(String[] args)
    {
        final Exchanger<List<Integer>> exchanger = new Exchanger<List<Integer>>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable()
        {
            public void run()
            {

                try
                {
                    List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
                    ExchangerDemo.printList(list1, "list1 init: ");
                    list1 = exchanger.exchange(list1);
                    ExchangerDemo.printList(list1, "list1 after exchanger: ");
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable()
        {
            public void run()
            {
                try
                {
                    List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(6, 7, 8, 9));
                    ExchangerDemo.printList(list2, "list2 init: ");
                    System.out.println("now list2 thread waiting...");
                    TimeUnit.SECONDS.sleep(4);
                    list2 = exchanger.exchange(list2);
                    ExchangerDemo.printList(list2, "list2 after exchanger: ");
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });

        /*
        list1 init: {1, 2, 3, 4, }
        list2 init: {6, 7, 8, 9, }
        now list2 thread waiting...

        .
        .
        .

        list2 after exchanger: {1, 2, 3, 4, }
        list1 after exchanger: {6, 7, 8, 9, }
        */

        try
        {
            TimeUnit.SECONDS.sleep(7);
            executorService.shutdownNow();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    }
}
