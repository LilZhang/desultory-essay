/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-19
 * Project        : desultory-essay
 * File Name      : App10.java
 */
public class App10
{
    public static void main(String[] args)
    {
        dataOutputIO();
        dataInputIO();
    }

    private static void dataOutputIO()
    {
        DataOutputStream out = null;

        try
        {
            out = new DataOutputStream(new FileOutputStream("E:\\DESKTOP\\data.out"));
            out.writeInt(366);
            out.writeFloat(3.66F);
            out.writeUTF("oks");
            out.flush();
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
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void dataInputIO()
    {
        DataInputStream in = null;

        try
        {
            in = new DataInputStream(new FileInputStream("E:\\DESKTOP\\data.out"));
            System.out.println(in.readInt());
            System.out.println(in.readFloat());
            System.out.println(in.readUTF());
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
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
