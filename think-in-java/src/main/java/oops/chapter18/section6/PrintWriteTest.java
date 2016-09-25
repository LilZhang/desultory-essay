/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section6;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-06
 * Project        : desultory-essay
 * File Name      : PrintWriteTest.java
 */
public class PrintWriteTest
{
    public static void main(String[] args)
    {
        File file = new File("E:\\DESKTOP\\out.txt");
        PrintWriter out = null;
        try
        {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            for (int i = 0; i < 5; i++)
            {
                out.print("lalala" + i);
                out.print(true);
                out.print(4.33d);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (out != null)
            {
                out.flush();
                out.close();
            }
        }
    }
}
