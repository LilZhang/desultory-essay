/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-09
 * Project        : desultory-essay
 * File Name      : BufferToText.java
 */
public class BufferToText
{
    public static void main(String[] args)
    {
        try
        {
            FileChannel channel = new FileOutputStream("E:\\DESKTOP\\out4.txt").getChannel();
            channel.write(ByteBuffer.wrap("some text".getBytes()));
            channel.close();

            FileChannel channel1 = new FileInputStream("E:\\DESKTOP\\out4.txt").getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel1.read(buffer);
            buffer.flip();
            System.out.println("buffer.asCharBuffer() : " + buffer.asCharBuffer());  // doesn't work : 乱码

            buffer.rewind();    // back to head
            String encoding = System.getProperty("file.encoding");
            CharBuffer decoded = Charset.forName(encoding).decode(buffer);      // 方法1: 从缓冲器中输出时解码
            System.out.println("decoded - " + encoding + " : " + decoded);      // works : some text

            //

            FileChannel channel2 = new FileOutputStream("E:\\DESKTOP\\out4.txt").getChannel();
            ByteBuffer buffer2 = ByteBuffer.wrap("another text".getBytes("UTF-16BE"));  // 方法2: 输入缓冲器时编码
            channel2.write(buffer2);
            channel2.close();

            FileChannel channel3 = new FileInputStream("E:\\DESKTOP\\out4.txt").getChannel();
            buffer2.clear();
            channel3.read(buffer2);
            buffer2.flip();
            System.out.println(buffer2.asCharBuffer());  // now it works : another text

            //

            FileChannel channel4 = new FileOutputStream("E:\\DESKTOP\\out4.txt").getChannel();
            ByteBuffer buffer3 = ByteBuffer.allocate(48);   // 空间 48字节 相当于24字符
            buffer3.asCharBuffer().put("charBuffer text");
            channel4.write(buffer3);
            channel4.close();

            channel4 = new FileInputStream("E:\\DESKTOP\\out4.txt").getChannel();
            buffer3.clear();
            channel4.read(buffer3);
            buffer3.flip();
            System.out.println(buffer3.asCharBuffer()); // charBuffer text
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
