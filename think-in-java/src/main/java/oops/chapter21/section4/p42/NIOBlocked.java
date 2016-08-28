/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section4.p42;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-08-28
 * Project        : desultory-essay
 * File Name      : NIOBlocked.java
 */
public class NIOBlocked implements Runnable
{
    private final SocketChannel socketChannel;

    public NIOBlocked(SocketChannel socketChannel)
    {
        this.socketChannel = socketChannel;
    }

    public void run()
    {
        System.out.println("waiting for read()");
        try
        {
            socketChannel.read(ByteBuffer.allocate(1));
        }
        catch (ClosedByInterruptException e)
        {
            // 被中断引发的异常
            System.out.println("ClosedByInterruptException");
        }
        catch (AsynchronousCloseException e)
        {
            // channel 在别处被关闭引发的异常
            System.out.println("AsynchronousCloseException");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        System.out.println("exiting NIOBlocked.run()" + this);
    }
}
