/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-20
 * Project        : desultory-essay
 * File Name      : App13.java
 */
public class App13
{
    public static void main(String[] args)
    {
        randomAccessFileRW();
    }

    private static void randomAccessFileRW()
    {
        // r:   只读
        // rw:  读写，写时不即时写入存储设备
        // rws: 读写，写时对文件的内容或元数据的每个更新都同步写入到底层存储设备。
        // rwd: 读写，写时对文件的内容的每个更新都同步写入到底层存储设备。(不包含元数据)

        RandomAccessFile randomAccessFile = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            randomAccessFile = new RandomAccessFile("E:\\DESKTOP\\io.txt", "rw");
            byte[] buffer = new byte[1024];
            while (randomAccessFile.read(buffer) != -1)
            {
                sb.append(new String(buffer, "UTF-8"));
            }

            int pos = sb.indexOf("一");
            String substring = sb.substring(pos);
            randomAccessFile.seek(pos);
            String input = "[break in by RandomAccessFile]\n" + substring;
            randomAccessFile.write(input.getBytes("UTF-8"));

            StringBuilder result = new StringBuilder();
            byte[] buffer2 = new byte[1024];
            randomAccessFile.seek(0);
            while (randomAccessFile.read(buffer2) != -1)
            {
                result.append(new String(buffer2, "UTF-8"));
            }

            System.out.println(result.toString());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (randomAccessFile != null)
            {
                try
                {
                    randomAccessFile.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
