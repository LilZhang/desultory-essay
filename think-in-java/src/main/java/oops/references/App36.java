/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-03
 * Project        : desultory-essay
 * File Name      : App36.java
 */
public class App36
{
    public static void main(String[] args)
    {
        final Exchanger<ExchangeObj> exchanger = new Exchanger<ExchangeObj>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable()
        {

            public void run()
            {
                try
                {
                    String name = Thread.currentThread().getName();
                    System.out.println(name + " put into Exchanger");
                    ExchangeObj exchangeObj = exchanger.exchange(new ExchangeObj("new obj init in " + name));
                    System.out.println(name + " get from Exchanger: " + exchangeObj);
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
                    String name = Thread.currentThread().getName();
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(name + " put into Exchanger");
                    ExchangeObj exchangeObj = exchanger.exchange(new ExchangeObj("new obj init in " + name));
                    System.out.println(name + " get from Exchanger: " + exchangeObj);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    private static class ExchangeObj
    {
        private String content;

        public ExchangeObj(String content)
        {
            this.content = content;
        }

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("ExchangeObj{");
            sb.append("content='").append(content).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
