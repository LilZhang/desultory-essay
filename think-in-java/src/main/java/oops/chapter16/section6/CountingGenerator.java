/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter16.section6;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-25
 * Project        : desultory-essay
 * File Name      : CountingGenerator.java
 */
public class CountingGenerator
{


    static char[] chars = ("abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    public static class Boolean implements Generator<java.lang.Boolean>
    {
        private boolean value = false;

        public java.lang.Boolean next()
        {
            value = !value;
            return value;
        }
    }

    public static class Byte implements Generator<java.lang.Byte>
    {
        private byte value = 0;

        public java.lang.Byte next()
        {
            return value++;
        }
    }

    public static class Character implements Generator<java.lang.Character>
    {
        int index = -1;

        public java.lang.Character next()
        {
            index = (index + 1) % chars.length;

            return chars[index];
        }
    }

    public static class String implements Generator<java.lang.String>
    {
        private int length = 7;
        Generator<java.lang.Character> cg = new Character();
        public String()
        {

        }

        public String(int length)
        {
            this.length = length;
        }

        public java.lang.String next()
        {
            char[] buf = new char[length];
            for (int i = 0; i < length; i++)
            {
                buf[i] = cg.next();
            }
            return new java.lang.String(buf);
        }
    }

    public static class Short implements Generator<java.lang.Short>
    {
        private short value = 0;

        public java.lang.Short next()
        {
            return value++;
        }
    }

    public static class Integer implements Generator<java.lang.Integer>
    {
        private int value = 0;

        public java.lang.Integer next()
        {
            return value++;
        }
    }

    public static class Long implements Generator<java.lang.Long>
    {
        private long value = 0;

        public java.lang.Long next()
        {
            return value++;
        }
    }

    public static class Float implements Generator<java.lang.Float>
    {
        private float value = 0F;

        public java.lang.Float next()
        {
            value += 0.1F;
            return value;
        }
    }

    public static class Double implements Generator<java.lang.Double>
    {
        private double value = 0d;

        public java.lang.Double next()
        {
            value += 0.1d;
            return value;
        }
    }

    interface Generator<T>
    {
        T next();
    }
}
