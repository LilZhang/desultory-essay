/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-01
 * Project        : desultory-essay
 * File Name      : ProducerConsumer.java
 */
class Result
{
    private final int id;

    public Result(int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

class Producer
{

}

class Consumer
{

}

class InterChange
{
    private Producer producer;

    private Consumer consumer;

    private Result result;

    public InterChange(Producer producer, Consumer consumer)
    {
        this.producer = producer;
        this.consumer = consumer;
    }

    public Producer getProducer()
    {
        return producer;
    }

    public void setProducer(Producer producer)
    {
        this.producer = producer;
    }

    public Consumer getConsumer()
    {
        return consumer;
    }

    public void setConsumer(Consumer consumer)
    {
        this.consumer = consumer;
    }

    public Result getResult()
    {
        return result;
    }

    public void setResult(Result result)
    {
        this.result = result;
    }
}

public class ProducerConsumer
{
    public static void main(String[] args)
    {
        final Producer producer = new Producer();
        final Consumer consumer = new Consumer();

        final InterChange interChange = new InterChange(producer, consumer);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable()
        {
            public void run()
            {
                try
                {
                    while (!Thread.interrupted())
                    {
                        // result 为空则等待
                        synchronized (consumer)
                        {
                            while (interChange.getResult() == null)
                            {
                                consumer.wait();
                            }
                        }
                        TimeUnit.MILLISECONDS.sleep(500);
                        System.out.println("result got by consumer" + interChange.getResult());
                        synchronized (producer)
                        {
                            interChange.setResult(null);
                            producer.notifyAll();
                        }
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("consumer interrupted");
                }
            }
        });
        executorService.execute(new Runnable()
        {
            public void run()
            {
                try
                {
                    int count = 0;
                    while (!Thread.interrupted())
                    {

                        synchronized (producer)
                        {
                            while (interChange.getResult() != null)
                            {
                                producer.wait();
                            }
                        }
                        TimeUnit.SECONDS.sleep(1);
                        Result result = new Result(++count);
                        interChange.setResult(result);
                        System.out.println("producer produce result" + result);
                        synchronized (consumer)
                        {
                            consumer.notifyAll();
                        }
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("producer interrupted");
                }
            }
        });
        try
        {
            TimeUnit.SECONDS.sleep(10);
            executorService.shutdownNow();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        /*
        producer produce resultResult{id=1}
        result got by consumerResult{id=1}
        producer produce resultResult{id=2}
        result got by consumerResult{id=2}
        producer produce resultResult{id=3}
        result got by consumerResult{id=3}
        producer produce resultResult{id=4}
        result got by consumerResult{id=4}
        producer produce resultResult{id=5}
        result got by consumerResult{id=5}
        producer produce resultResult{id=6}
        result got by consumerResult{id=6}
        producer interrupted
        consumer interrupted
        */
    }
}
