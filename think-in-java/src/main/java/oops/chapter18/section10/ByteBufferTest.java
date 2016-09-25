/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section10;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-09
 * Project        : desultory-essay
 * File Name      : ByteBufferTest.java
 */
public class ByteBufferTest
{
    public static void main(String[] args)
    {
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        buffer.asIntBuffer().put(234);
        System.out.println(buffer.getInt());

        buffer.rewind();
        buffer.asDoubleBuffer().put(4.33d);
        System.out.println(buffer.getDouble());

        buffer.rewind();
        IntBuffer intBuffer = buffer.asIntBuffer();
        intBuffer.put(new int[]{1, 2, 55, 66, 77});
        System.out.println(intBuffer.get(1));
        System.out.println(intBuffer.get(4));
        intBuffer.put(4, 777);
        System.out.println(intBuffer.get(4));

        try
        {
            ByteBuffer buffer2 = ByteBuffer.allocate(8);
            buffer2.put(new byte[]{0, 11, 0, 12, 0, 13, 0, 15});
            buffer2.position(0);
            ShortBuffer shortBuffer = buffer2.asShortBuffer();
            while (shortBuffer.hasRemaining())
            {
                System.out.print(shortBuffer.get() + ", ");
            }

            // 11, 12, 13, 15,

            System.out.println(Arrays.toString(buffer2.array()));

            // [0, 11, 0, 12, 0, 13, 0, 15]

            System.out.println();
            buffer2.order(ByteOrder.LITTLE_ENDIAN); // 低位优先

            shortBuffer = buffer2.asShortBuffer();
            while (shortBuffer.hasRemaining())
            {
                System.out.print(shortBuffer.get() + ", ");
            }

            // 2816, 3072, 3328, 3840,

            System.out.println(Arrays.toString(buffer2.array()));

            // [0, 11, 0, 12, 0, 13, 0, 15]



            ByteBuffer buffer3 = ByteBuffer.allocate(8);
            buffer3.order(ByteOrder.LITTLE_ENDIAN);
            buffer3.asShortBuffer().put(new short[]{11, 12, 13, 15});
            System.out.println(Arrays.toString(buffer3.array()));

            // [11, 0, 12, 0, 13, 0, 15, 0]


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
