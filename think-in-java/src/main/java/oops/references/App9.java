/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-17
 * Project        : desultory-essay
 * File Name      : App9.java
 */
// 操作 byte[] 时请一定要带上 Charset
public class App9
{
    private static final String CHARSET = "UTF-8";

    public static void main(String[] args)
    {
//        fileOutputIO();
//        bufferedOutputIO();
//        printStreamIO();
//        bufferedWriterIO();
//        printWriterIO();

//        fileInputIO();
//        bufferedInputIO();
//        fileReaderIO();
//        bufferedReaderIO();

//        byteArrayInputAndOutput();
//        byteArrayWriteAndRead();

//        fileOutputNIO();
        fileInputNIO();
    }

    // IO

    private static void fileOutputIO()
    {
        OutputStream out = null;
        try
        {
            out = new FileOutputStream("E:\\DESKTOP\\io.txt");

            out.write("line1\n".getBytes(CHARSET));
            out.write("line2\n".getBytes(CHARSET));
            out.write("line3\n".getBytes(CHARSET));
            out.write("line4\n".getBytes(CHARSET));
            out.write("一二三四五\n".getBytes(CHARSET));
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
                catch (Exception e)
                {
                    // ginore
                }
            }
        }
    }

    private static void bufferedOutputIO()
    {
        OutputStream out = null;
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("E:\\DESKTOP\\io.txt");
            out = new BufferedOutputStream(fileOutputStream);
            out.write("line1\n".getBytes(CHARSET));
            out.write("line2\n".getBytes(CHARSET));
            out.write("line3\n".getBytes(CHARSET));
            out.write("line4\n".getBytes(CHARSET));

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
                catch (Exception e)
                {
                    // ginore
                }
            }
        }
    }

    // charset can not be set
    private static void bufferedWriterIO()
    {
        Writer writer = null;

        try
        {
            FileWriter fileWriter = new FileWriter("E:\\DESKTOP\\io4.txt");
            writer = new BufferedWriter(fileWriter);

            writer.write("line1\n");
            writer.write("line2\n");
            writer.write("line3\n");
            writer.write("line4\n");
            writer.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException e)
            {
                // ignore
            }
        }
    }

    private static void printStreamIO()
    {
        PrintStream printStream = null;
        try
        {
            printStream = new PrintStream("E:\\DESKTOP\\io3.txt", CHARSET);
            printStream.println("line1");
            printStream.println("line2");
            printStream.println("line3");
            printStream.println("line4");
            printStream.flush();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (printStream != null)
            {
                printStream.close();
            }
        }
    }

    // charset can not be set
    private static void printWriterIO()
    {
        Writer writer = null;

        try
        {
            writer = new PrintWriter(new BufferedWriter(new FileWriter("E:\\DESKTOP\\io5.txt")));

            writer.append('a');
            writer.append('b');
            writer.append('c');
            writer.append('d');
            writer.append('e');

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (writer != null)
            {
                try
                {
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void fileInputIO()
    {
        InputStream in = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            in = new FileInputStream("E:\\DESKTOP\\io.txt");

            byte[] buffer = new byte[2048];
            if ((in.read(buffer)) != -1)
            {
                sb.append(new String(buffer, CHARSET));
            }
            System.out.println(sb.toString());
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

    private static void bufferedInputIO()
    {
        InputStream in = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            in = new BufferedInputStream(new FileInputStream("E:\\DESKTOP\\io.txt"));
            byte[] buffer = new byte[4096];
            while (in.read(buffer) != -1)
            {
                sb.append(new String(buffer, CHARSET));
            }
            System.out.println(sb.toString());
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
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    // charset can not be set
    private static void fileReaderIO()
    {
        Reader reader = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            reader = new FileReader("E:\\DESKTOP\\io.txt");
            char[] buffer = new char[4096];
            if ((reader.read(buffer)) != -1)
            {
                sb.append(buffer);
            }
            System.out.println(sb.toString());
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

    // charset can not be set
    private static void bufferedReaderIO()
    {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            reader = new BufferedReader(new FileReader("E:\\DESKTOP\\io.txt"));
            String str = null;
            while ((str = reader.readLine()) != null)
            {
                sb.append(str);
                sb.append("\n");
            }
            System.out.println(sb.toString());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void byteArrayInputAndOutput()
    {
        OutputStream out = null;
        InputStream in = null;
        StringBuilder sb = new StringBuilder();

        try
        {
            ByteArrayOutputStream baout = new ByteArrayOutputStream();

            out = new BufferedOutputStream(baout);
            out.write("line5\n".getBytes(CHARSET));
            out.write("line6\n".getBytes(CHARSET));
            out.write("line7\n".getBytes(CHARSET));
            out.write("line8\n".getBytes(CHARSET));
            out.flush();

            in = new BufferedInputStream(
                    new ByteArrayInputStream(baout.toByteArray()));
            byte[] buffer = new byte[2048];
            if ((in.read(buffer)) != -1)
            {
                sb.append(new String(buffer, CHARSET));
            }
            System.out.println(sb.toString());
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

    private static void byteArrayWriteAndRead()
    {
        Writer writer = null;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();

        ByteArrayOutputStream baout = new ByteArrayOutputStream();
        try
        {
            writer = new BufferedWriter(new OutputStreamWriter(baout, CHARSET));
            writer.write("line22\n".toCharArray());
            writer.write("line33\n".toCharArray());
            writer.write("line44\n".toCharArray());
            writer.write("line55\n".toCharArray());
            writer.flush();

            byte[] bytes = baout.toByteArray();
            System.out.println(Arrays.toString(bytes));

            reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes), CHARSET));
            String str = null;
            while ((str = reader.readLine()) != null)
            {
                sb.append(str);
                sb.append("\n");
            }
            System.out.println(sb.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // NIO

    private static void fileOutputNIO()
    {
        FileChannel channel = null;

        try
        {
            channel = new FileOutputStream("E:\\DESKTOP\\nio.txt").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.wrap("line111\n一二三四五\nline333\n".getBytes(CHARSET));
            channel.write(byteBuffer);
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
            if (channel != null)
            {
                try
                {
                    channel.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void fileInputNIO()
    {
        FileChannel channel = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            channel = new FileInputStream("E:\\DESKTOP\\nio.txt").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            while (channel.read(byteBuffer) != -1)
            {
                byteBuffer.flip();
                CharBuffer charBuffer = Charset.forName(CHARSET).decode(byteBuffer);
                sb.append(charBuffer);
                byteBuffer.flip();
            }
            System.out.println(sb.toString());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
