/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section11;

import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-11
 * Project        : desultory-essay
 * File Name      : ZipCompress.java
 */
public class ZipCompress
{
    private static String OUT_URL = "E:\\DESKTOP\\test.zip";

    public static void main(String[] args)
    {
        List<String> fileNameList = Arrays.asList(
                "E:\\DESKTOP\\out.txt",
                "E:\\DESKTOP\\out2.txt",
                "E:\\DESKTOP\\out3.txt",
                "E:\\DESKTOP\\out7.txt");

        try
        {
            FileOutputStream fos = new FileOutputStream(OUT_URL);
            CheckedOutputStream cos = new CheckedOutputStream(fos, new Adler32());
            // 两种Checksum(校验和)类型:
            // Adler32 (a bit faster), CRC32 (slower but with more accuracy)

            ZipOutputStream zos = new ZipOutputStream(cos);
            BufferedOutputStream out = new BufferedOutputStream(zos);

            /*BufferedOutputStream out = new BufferedOutputStream(
                    new ZipOutputStream(
                            new CheckedOutputStream(
                                    new FileOutputStream(OUT_URL), new Adler32())));*/

            zos.setComment("A test of java zipping");

            for (String fileName : fileNameList)
            {
                System.out.println("writing file " + fileName);

                BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName));

                zos.putNextEntry(new ZipEntry(getFileName(fileName)));

                int c;
                while ((c = in.read()) != -1)
                {
                    out.write(c);
                }

                in.close();
                out.flush();
            }

            out.close();

            read2();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static String getFileName(String path)
    {
        String result = "";
        int i = path.lastIndexOf("\\");
        result = path.substring(i + 1);
        return result;
    }

    private static void read() throws IOException
    {
        FileInputStream fis = new FileInputStream(OUT_URL);

        CheckedInputStream cis = new CheckedInputStream(fis, new Adler32());

        ZipInputStream zis = new ZipInputStream(cis);

        BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(zis)/*, "UTF-8"*/));

        /*BufferedInputStream in = new BufferedInputStream(
                new ZipInputStream(
                        new CheckedInputStream(
                                new FileInputStream(OUT_URL), new Adler32())));*/

        ZipEntry zipEntry;

        while ((zipEntry = zis.getNextEntry()) != null)
        {
            System.out.println("Reading file " + zipEntry);
            String c;
            while ((c = in.readLine()) != null && c.length() > 0)
            {
                System.out.println((c));
            }
        }

        System.out.println("Checksum: " + cis.getChecksum().getValue());
        in.close();
    }

    // alternative way to read
    private static void read2() throws IOException
    {
        ZipFile zipFile = new ZipFile(OUT_URL);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        BufferedReader in = null;

        while (entries.hasMoreElements())
        {
            ZipEntry zipEntry = entries.nextElement();

            System.out.println("Reading file " + zipEntry);
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            in = new BufferedReader(new InputStreamReader(inputStream/*, "UTF-8"*/));
            String c;
            while ((c = in.readLine()) != null && c.length() > 0)
            {
                System.out.println(c);
            }
        }

        in.close();
    }
}
