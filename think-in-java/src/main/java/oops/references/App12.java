/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-19
 * Project        : desultory-essay
 * File Name      : App12.java
 */
public class App12
{
    public static void main(String[] args)
    {
        printProcessWithInputIO();
    }

    private static void printProcessWithInputIO()
    {
        InputStream in = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder("ipconfig");
            Process process = processBuilder.start();
            in = process.getInputStream();
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1)
            {
                sb.append(new String(buffer, "GBK"));
            }

            System.out.println(sb.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void printProcessWithReader()
    {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder("ipconfig");
            Process process = processBuilder.start();
            reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String str = null;
            while ((str = reader.readLine()) != null)
            {
                sb.append(str);
            }
            System.out.println(sb.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }
}
