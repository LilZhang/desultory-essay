/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter2.section4;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-16
 * Project        : desultory-essay
 * File Name      : DefaultValueTest.java
 */
public class DefaultValueTest
{
    public static void main(String[] args)
    {
        Obj obj = new Obj();

        //all true
        System.out.println(obj.b == false);
        System.out.println(obj.c == '\u0000');
        System.out.println(obj.by == (byte) 0);
        System.out.println(obj.s == (short) 0);
        System.out.println(obj.i == 0);
        System.out.println(obj.l == 0L);
        System.out.println(obj.f == 0f);
        System.out.println(obj.d == 0d);

        System.out.println(obj);
    }

    public static class Obj
    {
        boolean b;
        char c;
        byte by;
        short s;
        int i;
        long l;
        float f;
        double d;

        @Override
        public String toString()
        {
            /*final StringBuilder sb = new StringBuilder("Obj{");
            sb.append("b=").append(b);
            sb.append(", c=").append(c);
            sb.append(", by=").append(by);
            sb.append(", s=").append(s);
            sb.append(", i=").append(i);
            sb.append(", l=").append(l);
            sb.append(", f=").append(f);
            sb.append(", d=").append(d);
            sb.append('}');
            return sb.toString();*/

            return super.toString();
        }
    }
}
