/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section6;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-07
 * Project        : desultory-essay
 * File Name      : DataOutputStreamTest.java
 */
public class DataOutputStreamTest
{
    public static void main(String[] args)
    {
        DataOutputStream out = null;
        try
        {
            out = new DataOutputStream(new BufferedOutputStream(
                    new FileOutputStream(new File("E:\\DESKTOP\\out2.txt"))));
            out.writeInt(666);
            out.writeUTF("int 1");
            out.writeInt(666);
            out.writeUTF("int 2");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.flush();
                    out.close();
                }
                catch (Exception e)
                {
                    // ignore
                }

            }
        }
    }
}
