/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-08-20
 * Project        : desultory-essay
 * File Name      : App2.java
 */
public class App2
{
    private static byte[] bytes = new byte[0];

    public static void main(String[] args)
    {
        Set<Integer> set = IntStream
                .rangeClosed(0, 100)
                .boxed()
                .collect(Collectors.toSet());

        System.out.println("100 start.");
        long start = System.currentTimeMillis();

        set.forEach((i) ->
        {
            BitSet bitSet = inflater(bytes);
            bitSet.set(i);
            bytes = deflater(bitSet);
        });

        long end = System.currentTimeMillis();
        System.out.println("100 put: " + (end - start));
    }


    // 解压缩
    public static BitSet inflater(byte[] bytes)
    {
        ByteArrayOutputStream baos = null;
        InflaterOutputStream ios = null;
        BitSet result = null;

        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            baos = new ByteArrayOutputStream();
            ios = new InflaterOutputStream(baos, new Inflater(true));

            byte[] b = new byte[1024];
            int len;
            while ((len = bais.read(b)) != -1)
            {
                ios.write(b, 0, len);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // bais.close();
            if (ios != null)
            {
                try
                {
                    ios.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }

        if (baos != null)
        {
            result = BitSet.valueOf(baos.toByteArray());
        }
        return result;
    }

    // 压缩
    public static byte[] deflater(BitSet bitSet)
    {
        byte[] bytes = bitSet.toByteArray();
        ByteArrayOutputStream baos = null;
        DeflaterOutputStream dos = null;
        byte[] result = null;

        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            baos = new ByteArrayOutputStream();
            dos = new DeflaterOutputStream(baos, new Deflater(1, true));   // COMPRESSION LEVEL 1

            byte[] b = new byte[1024];
            int len;
            while ((len = bais.read(b)) != -1)
            {
                dos.write(b, 0, len);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // bais.close();
            // baos.close();
            if (dos != null)
            {
                try
                {
                    dos.flush();
                    dos.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }

        if (baos != null)
        {
            result = baos.toByteArray();
        }
        return result;
    }
}