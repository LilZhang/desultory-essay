/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section10;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-10
 * Project        : desultory-essay
 * File Name      : MappedAndStreamTiming.java
 */
public class MappedAndStreamTiming
{
    private static int num = 8000000;

    private static String url = "E:\\DESKTOP\\test.txt";

    public static void main(String[] args)
    {
        Tester[] testers = new Tester[]{
                new Tester("Stream Write")
                {
                    @Override
                    public void test() throws IOException
                    {
                        DataOutputStream out = new DataOutputStream(
                                new BufferedOutputStream(
                                        new FileOutputStream(url)));

                        for (int i = 0; i < num; i++)
                        {
                            out.writeInt(i);
                        }

                        out.close();
                    }
                },
                new Tester("Mapped Write")
                {
                    @Override
                    public void test() throws IOException
                    {
                        FileChannel channel = new RandomAccessFile(url, "rw")
                                .getChannel();
                        IntBuffer intBuffer = channel
                                .map(FileChannel.MapMode.READ_WRITE, 0, channel.size()).asIntBuffer();
                        for (int i = 0; i < num; i++)
                        {
                            intBuffer.put(i);
                        }
                        channel.close();

                    }
                },
                new Tester("Stream Read")
                {
                    @Override
                    public void test() throws IOException
                    {
                        DataInputStream in = new DataInputStream(
                                new BufferedInputStream(
                                        new FileInputStream(url)));

                        for (int i = 0; i < num; i++)
                        {
                            in.readInt();
                        }
                        in.close();
                    }
                },
                new Tester("Mapped Read")
                {
                    @Override
                    public void test() throws IOException
                    {
                        FileChannel channel = new FileInputStream(url)
                                .getChannel();
                        IntBuffer intBuffer = channel
                                .map(FileChannel.MapMode.READ_ONLY, 0, channel.size())
                                .asIntBuffer();
                        while (intBuffer.hasRemaining())
                        {
                            intBuffer.get();
                        }
                        channel.close();
                    }
                },
                new Tester("Stream Read/Write")
                {
                    @Override
                    public void test() throws IOException
                    {
                        RandomAccessFile raf = new RandomAccessFile(url, "rw");
                        raf.writeInt(1);
                        for (int i = 0; i < num / 2; i++)
                        {
                            raf.seek(raf.length() - 4);
                            raf.writeInt(raf.readInt());
                        }
                        raf.close();
                    }
                },
                new Tester("Mapped Read/Write")
                {
                    @Override
                    public void test() throws IOException
                    {
                        FileChannel channel = new RandomAccessFile(url, "rw").getChannel();
                        IntBuffer intBuffer = channel
                                .map(FileChannel.MapMode.READ_WRITE, 0, channel.size())
                                .asIntBuffer();
                        intBuffer.put(0);
                        for (int i = 0; i < num / 2; i++)
                        {
                            intBuffer.put(intBuffer.get(i - 1));
                        }
                        channel.close();
                    }
                }
        };

        for (Tester tester : testers)
        {
            tester.runTest();
        }
    }

    private abstract static class Tester
    {
        private String name;

        public Tester(String name)
        {
            this.name = name;
        }

        public void runTest()
        {
            try
            {
                long start = System.nanoTime();
                test();
                long duration = System.nanoTime() - start;
                System.out.format(name + " : %.2fn\n", duration / 1.0E9);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public abstract void test() throws IOException;
    }
}
