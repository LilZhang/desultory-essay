/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section10;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-10
 * Project        : desultory-essay
 * File Name      : LargeMappedFiles.java
 */
public class LargeMappedFiles
{
    static int length = 0x8FFFFFF;

    public static void main(String[] args)
    {
        try
        {
            MappedByteBuffer out = new RandomAccessFile("E:\\DESKTOP\\out6.txt", "rw")
                    .getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);

            /*MappedByteBuffer out2 = new RandomAccessFile("E:\\WORK\\crawledData\\dqdata\\changshu\\detail.txt", "r")
                    .getChannel().map(FileChannel.MapMode.READ_ONLY, 0, length);
            CharBuffer charBuffer = Charset.forName("UTF-8").decode(out2);*/

            for (int i = 0; i < length; i++)
            {
                out.put(((byte) 'x'));
            }

            // finished writing

            for (int i = length / 2; i < length / 2 + 6; i++)
            {
                System.out.println(((char) out.get(i)));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
