/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section11;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-11
 * Project        : desultory-essay
 * File Name      : GZIPcompress.java
 */
public class GZIPcompress
{
    private static String URL = "";

    private static String OUT_URL = ""; // *.gz

    public static void main(String[] args)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(URL));

            BufferedOutputStream out = new BufferedOutputStream(
                    new GZIPOutputStream(
                            new FileOutputStream(OUT_URL)));

            int c;
            while ((c = in.read()) != -1)
            {
                out.write(c);
            }
            in.close();
            out.close();

            //

            BufferedReader in2 = new BufferedReader(
                    new InputStreamReader(
                            new GZIPInputStream(
                                    new FileInputStream(OUT_URL))));

            String s;
            while ((s = in2.readLine()) != null)
            {
                System.out.println(s);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
