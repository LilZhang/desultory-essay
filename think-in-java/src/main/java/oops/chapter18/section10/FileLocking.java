/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section10;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lili.zhang
 * Create Date    : 2016-08-11
 * Project        : desultory-essay
 * File Name      : FileLocking.java
 */
public class FileLocking
{
    public static void main(String[] args)
    {
        try
        {
            FileOutputStream out = new FileOutputStream("test.txt");
            FileLock fileLock = out.getChannel().tryLock();     // 锁全部文件
            // out.getChannel().tryLock(0, 100, false);         // 锁部分
            fileLock.isShared();                                // 查看是否shared
            if (fileLock != null)
            {
                TimeUnit.MILLISECONDS.sleep(100);
                fileLock.release();                             // 释放锁
            }
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
