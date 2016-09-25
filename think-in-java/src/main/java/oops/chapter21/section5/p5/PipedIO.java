/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p5;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-03
 * Project        : desultory-essay
 * File Name      : PipedIO.java
 */
class Sender implements Runnable
{
    private Random random = new Random(47);

    private PipedWriter out = new PipedWriter();

    public PipedWriter getOut()
    {
        return out;
    }

    public void run()
    {
        try
        {
            while (true)
            {
                for (char c = 'A'; c <= 'z'; c++)
                {
//                    System.out.println("write: " + c);
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(400));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            System.out.println("Sender interrupt");
        }
        System.out.println("Sender off");
    }
}

class Receiver implements Runnable
{
    private PipedReader in;

    public Receiver(PipedReader in)
    {
        this.in = in;
    }

    public void run()
    {
        try
        {
            while (true)
            {
                System.out.println("read: " + ((char) in.read()));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Receiver off");
    }
}

public class PipedIO
{
    public static void main(String[] args) throws Exception
    {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(new PipedReader(sender.getOut()));
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(sender);
        executorService.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        executorService.shutdownNow();
    }
}
