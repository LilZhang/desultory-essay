/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-27
 * Project        : desultory-essay
 * File Name      : App19.java
 */
public class App19
{
    private static final String OUT_PATH = "/home/lilzhang/Desktop/datao.zip";

    private static final List<String> PATH_LIST = Arrays.asList(
            "/home/lilzhang/Desktop/datao.txt",
            "/home/lilzhang/Desktop/agree.txt"
    );

    public static void main(String[] args)
    {
        InputStream in = null;
        OutputStream out = null;

        try
        {
            FileOutputStream fout = new FileOutputStream(OUT_PATH);
            CheckedOutputStream cout = new CheckedOutputStream(fout, new Adler32());
            ZipOutputStream zout = new ZipOutputStream(cout);
            out = new BufferedOutputStream(zout);

            zout.setComment("just for test");

            for (String filePath : PATH_LIST)
            {
                FileInputStream fin = new FileInputStream(filePath);

                String fileName = getFileName(filePath);
                System.out.println("now writing " + fileName);
                zout.putNextEntry(new ZipEntry(fileName));

                int len;
                byte[] buffer = new byte[1024];

                while ((len = fin.read(buffer)) != -1)
                {
                    out.write(buffer, 0, len);
                }
                out.flush();

                fin.close();
            }

            System.out.println(cout.getChecksum().getValue());
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
            CheckedInputStream cin = new CheckedInputStream(fin, new Adler32());
            ZipInputStream zin = new ZipInputStream(cin);
            in = new BufferedInputStream(zin);

            ZipEntry zipEntry;
            int len;
            byte[] buffer = new byte[1024];
            while ((zipEntry = zin.getNextEntry()) != null)
            {
                System.out.println("now reading " + zipEntry.getName());
                StringBuilder sb = new StringBuilder();

                while ((len = in.read(buffer)) != -1)
                {
                    sb.append(new String(buffer, 0, len, "UTF-8"));
                }

                System.out.println(sb.toString());
            }
            System.out.println(cin.getChecksum().getValue());
        }
        catch (IOException e)
        {

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

    private static String getFileName(String filePath)
    {
        String result = "";

        if (filePath != null && filePath.length() > 0)
        {
            int index = filePath.lastIndexOf(File.separatorChar);
            result = filePath.substring(index + 1);
        }

        return result;
    }
}
