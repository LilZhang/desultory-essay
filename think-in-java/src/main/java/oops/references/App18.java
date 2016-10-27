/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-27
 * Project        : desultory-essay
 * File Name      : App18.java
 */
public class App18
{
    private static final String PATH = "/home/lilzhang/Desktop/datao.txt";

    private static final String OUT_PATH = "/home/lilzhang/Desktop/datao.gz";

    private static final String UTF_8 = "UTF-8";

    public static void main(String[] args)
    {
        InputStream in = null;
        OutputStream out = null;

        try
        {
            FileInputStream fin = new FileInputStream(PATH);
            in = new BufferedInputStream(fin);

            FileOutputStream fout = new FileOutputStream(OUT_PATH);
            GZIPOutputStream gzout = new GZIPOutputStream(fout);
            out = new BufferedOutputStream(gzout);

            int len;
            byte[] buffer = new byte[1024];

            while ((len = in.read(buffer)) != -1)
            {
                out.write(buffer, 0, len);
            }
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

        // read

        try
        {
            FileInputStream fin = new FileInputStream(OUT_PATH);
            GZIPInputStream gzin = new GZIPInputStream(fin);
            in = new BufferedInputStream(gzin);

            int len;
            byte[] buffer = new byte[1024];
            StringBuilder sb = new StringBuilder();

            while ((len = in.read(buffer)) != -1)
            {
                sb.append(new String(buffer, 0, len, UTF_8));
            }

            System.out.println(sb.toString());
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
}
