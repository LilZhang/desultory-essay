/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section4.p44;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-28
 * Project        : desultory-essay
 * File Name      : InterruptingIdiom.java
 */

// 中断的时机：
//    1. 即将进入到阻塞操作中
//    2. 线程本身在阻塞操作中

//        可中断的阻塞操作：
//        1. sleep()
//        2. wait() until notify()

// 当出现无法中断的情况
// 可以使用 Thread.interrupted() 来查看状态是否中断
// 然后编写可靠的清理语句

class NeedsCleanup
{
    private final int id;

    public NeedsCleanup(int id)
    {
        this.id = id;
        System.out.println("Needscleanup " + id);
    }

    public void cleanup()
    {
        System.out.println("now cleaning up " + this);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("NeedsCleanup{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

class Blocked3 implements Runnable
{
    private volatile double d = 0d;

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                // point 1
                NeedsCleanup nc1 = new NeedsCleanup(1);

                try
                {
                    System.out.println("sleeping");
                    TimeUnit.SECONDS.sleep(1);

                    // point 2
                    NeedsCleanup nc2 = new NeedsCleanup(2);
                    try
                    {
                        System.out.println("calculating");
                        for (int i = 0; i < 2500000; i++)
                        {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("finished time-consuming operation");
                    }
                    finally
                    {
                        nc2.cleanup();
                    }
                }
                finally
                {
                    nc1.cleanup();
                }
            }
            System.out.println("exting via while() test");
        }
        catch (InterruptedException e)
        {
            System.out.println(Thread.interrupted());
            System.out.println("exting via InterruptedException");
        }
    }
}

public class InterruptingIdiom
{
    public static void main(String[] args)
    {
        try
        {
            Thread thread = new Thread(new Blocked3());
            thread.start();
            TimeUnit.MILLISECONDS.sleep(1001);
            thread.interrupt();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /* param: 800
        Needscleanup 1
        sleeping
        now cleaning up NeedsCleanup{id=1}  // always do this
        false
        exting via InterruptedException
        */

//        当 interrupt() 在 point 1 和 point 2 之间：
//        -> sleep() 为阻塞操作
//            -> 阻塞时中断，抛出 InterruptException
//                -> 执行 finally 块清理

//        当 interrupt() 在 point 2 之后：
//        -> 接下来没有阻塞操作了
//            -> 一路执行到循环体结束，进入 finally 块执行清理
//                -> 回到 while 顶，依靠 Thread.interrupted() 判断，跳出。

    }
}
