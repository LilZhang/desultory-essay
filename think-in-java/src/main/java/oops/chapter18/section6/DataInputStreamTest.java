/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section6;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-07
 * Project        : desultory-essay
 * File Name      : DataInputStreamTest.java
 */
public class DataInputStreamTest
{
    public static void main(String[] args)
    {
        DataInputStream in = null;
        try
        {
            in = new DataInputStream(
                    new BufferedInputStream(new FileInputStream("E:\\DESKTOP\\out2.txt")));
            System.out.println();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (Exception e)
                {
                    // ignore
                }
            }
        }
    }
}
