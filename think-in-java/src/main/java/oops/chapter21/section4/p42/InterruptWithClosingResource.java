/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section4.p42;

import oops.chapter21.section4.p41.IOBlocked;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-08-28
 * Project        : desultory-essay
 * File Name      : InterruptWithClosingResource.java
 */
public class InterruptWithClosingResource
{
    // 可以关闭底层IO来达到中断
    public static void main(String[] args)
    {
        try
        {
            ExecutorService executorService = Executors.newCachedThreadPool();
            ServerSocket server = new ServerSocket(8080);
            InputStream socketInput = new Socket("localhost", 8080).getInputStream();
            executorService.execute(new IOBlocked(socketInput));
            executorService.execute(new IOBlocked(System.in));
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("shutdown all threads");
            executorService.shutdownNow();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("closing " + socketInput.getClass().getSimpleName());
            socketInput.close();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("closing " + System.in.getClass().getSimpleName());
            System.in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /*waiting for read().
            waiting for read().
            shutdown all threads
        closing SocketInputStream
        interrupted from blocked I/O
        exiting SynchronizedBlocked.run()
        closing BufferedInputStream*/
    }
}
