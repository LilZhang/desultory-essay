/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section7.p7;

import oops.chapter21.section7.p6.Fat;

import java.util.List;
import java.util.concurrent.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-06
 * Project        : desultory-essay
 * File Name      : ExchangerDemo.java
 */
class ExchangerProducer<T> implements Runnable
{
    private Generator<T> generator;

    private Exchanger<List<T>> exchanger;

    private List<T> holder;

    public ExchangerProducer(Exchanger<List<T>> exchanger, Generator<T> generator, List<T> holder)
    {
        this.exchanger = exchanger;
        this.generator = generator;
        this.holder = holder;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                for (int i = 0; i < ExchangerDemo.SIZE; i++)
                {
                    holder.add(generator.next());
                }
                ExchangerDemo.printList(holder, "ExchangerProducer: before exchange: ");
                holder = exchanger.exchange(holder);
                ExchangerDemo.printList(holder, "ExchangerProducer: after exchange: ");
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("ExchangerProducer exiting via interrupt");
        }
    }
}

class ExchangerConsumer<T> implements Runnable
{
    private Exchanger<List<T>> exchanger;

    private List<T> holder;

    private volatile T value;

    public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder)
    {
        this.exchanger = exchanger;
        this.holder = holder;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                ExchangerDemo.printList(holder, "ExchangerConsumer: before exchange: ");
                holder = exchanger.exchange(holder);
                ExchangerDemo.printList(holder, "ExchangerConsumer: after exchange: ");
                for (T t : holder)
                {
                    value = t;
                    holder.remove(t);
                }
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("ExchangerConsumer exiting via interrupt");
        }
        System.out.println("Final value: " + value);
    }
}

interface Generator<T>
{
    T next();
}

public class ExchangerDemo
{
    static int SIZE = 4;

    static int DELAY = 3;

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Exchanger<List<Fat>> xc = new Exchanger<List<Fat>>();

        List<Fat> producerList = new CopyOnWriteArrayList<Fat>();
        List<Fat> consumerList = new CopyOnWriteArrayList<Fat>();

        executorService.execute(new ExchangerProducer<Fat>(xc, new Generator<Fat>()
        {
            public Fat next()
            {
                return new Fat();
            }
        }, producerList));

        executorService.execute(new ExchangerConsumer<Fat>(xc,consumerList));

        try
        {
            TimeUnit.SECONDS.sleep(DELAY);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }

    public static <T> void printList(List<T> list, String title)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(title);
        sb.append("{");
        for (T t : list)
        {
            sb.append(t);
            sb.append(", ");
        }
        sb.append("}");

        System.out.println(sb.toString());
    }
}
