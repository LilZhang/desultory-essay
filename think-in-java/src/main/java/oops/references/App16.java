/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-26
 * Project        : desultory-essay
 * File Name      : App16.java
 */
public class App16
{
    private static final String PATH = "/home/lilzhang/Desktop/datao.txt";

    public static void main(String[] args) throws IOException
    {
        FileChannel channel = new RandomAccessFile(PATH, "rw").getChannel();
        FileLock fileLock = channel.tryLock(2, 3, false);
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 2, 3);
        CharBuffer charBuffer = Charset.forName("UTF-8").decode(mappedByteBuffer);
//        charBuffer.rewind();
//        charBuffer.put(0, '?');
//        charBuffer.put(1, '!');

        if (fileLock != null)
        {
            fileLock.release();
        }
        channel.close();
    }
}
