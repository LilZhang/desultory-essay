/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-07
 * Project        : desultory-essay
 * File Name      : Nio.java
 */
public class Nio
{
    public static void main(String[] args)
    {
        try
        {
            // write
            FileChannel channel = new FileOutputStream("E:\\DESKTOP\\out3.txt")
                    .getChannel();
            channel.write(ByteBuffer.wrap("some text".getBytes())); // some text
            System.out.println(channel.size());                     // 9
            channel.position(4);
            channel.write(ByteBuffer.wrap("some text".getBytes())); // somesome text
            System.out.println(channel.size());                     // 13
            channel.close();

            // read
            FileChannel channel1 = new FileInputStream("E:\\DESKTOP\\out3.txt")
                    .getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            channel1.read(byteBuffer);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining())
            {
                System.out.print(((char) byteBuffer.get()));
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Deprecated
    private static void transfer(String srcPath, String destPath)
    {
        FileChannel in = null;
        FileChannel out = null;
        try
        {
            in = new FileInputStream(srcPath).getChannel();
            out = new FileOutputStream(destPath).getChannel();

            ByteBuffer buff = ByteBuffer.allocate(1024);
            while (in.read(buff) != -1)
            {
                buff.flip();    // prepare for writing
                out.write(buff);
                buff.clear();   // prepare for reading
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void betterTransfer(String srcPath, String destPath)
    {
        FileChannel in = null;
        FileChannel out = null;
        try
        {
            in = new FileInputStream(srcPath).getChannel();
            out = new FileOutputStream(destPath).getChannel();

            in.transferTo(0, in.size(), out);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
