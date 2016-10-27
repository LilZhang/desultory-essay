/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-26
 * Project        : desultory-essay
 * File Name      : App15.java
 */
public class App15
{
    public static void main(String[] args)
    {
        ByteBuffer byteBuffer = ByteBuffer.allocate(12);
        byteBuffer.put(new byte[]{0, 61, 0, 62, 0, 63});
        byteBuffer.rewind();
        CharBuffer charBuffer = byteBuffer.asCharBuffer();

        charBuffer.put(3, 's');

        while (byteBuffer.hasRemaining())
        {
            System.out.println(byteBuffer.get());
        }
    }
}
