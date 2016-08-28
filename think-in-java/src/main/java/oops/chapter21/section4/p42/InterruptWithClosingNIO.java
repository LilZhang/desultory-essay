/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section4.p42;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-28
 * Project        : desultory-essay
 * File Name      : InterruptWithClosingNIO.java
 */
public class InterruptWithClosingNIO
{
    // nio可以被中断
    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try
        {
//            ServerSocket server = new ServerSocket(8080);
            InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
            SocketChannel sc1 = SocketChannel.open(isa);
            SocketChannel sc2 = SocketChannel.open(isa);
            Future<?> future = executorService.submit(new NIOBlocked(sc1));
            executorService.execute(new NIOBlocked(sc2));
            executorService.shutdown();
            TimeUnit.SECONDS.sleep(1);

            // interrupt
            future.cancel(true);
            TimeUnit.SECONDS.sleep(1);

            // release the block by closing the channel
            sc2.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /*waiting for read()
        waiting for read()
        ClosedByInterruptException
        exiting NIOBlocked.run()oops.chapter21.section4.p32.NIOBlocked@30a1718e
        AsynchronousCloseException
        exiting NIOBlocked.run()oops.chapter21.section4.p32.NIOBlocked@2ecfa52e*/

    }
}
