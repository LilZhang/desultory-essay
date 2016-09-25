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
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-09
 * Project        : desultory-essay
 * File Name      : WriteByteToChar.java
 */
public class WriteByteToChar
{
    public static void main( String[] args )
    {
        try
        {
            FileChannel channel = new FileOutputStream("E:\\DESKTOP\\out6.txt").getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(2048);
            buffer.put(new byte[]{97, 98, 99, 100, 101});
            buffer.position(0);
            channel.write(buffer);
            channel.close();

            channel = new FileInputStream("E:\\DESKTOP\\out6.txt").getChannel();
            buffer.clear();
            channel.read(buffer);
            buffer.flip();
            CharBuffer decode = Charset.forName("UTF-8").decode(buffer);

            System.out.println(decode); // abcde
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
