/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter2.section6;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-16
 * Project        : desultory-essay
 * File Name      : StaticValue.java
 */
public class StaticValue
{
    public static void main(String[] args)
    {
        Obj obj1 = new Obj();
        Obj obj2 = new Obj();

        System.out.println(obj1.STATIC_VALUE);  //55
        System.out.println(obj2.STATIC_VALUE);  //55

        obj1.STATIC_VALUE = 22;

        System.out.println(obj1.STATIC_VALUE);  //22
        System.out.println(obj2.STATIC_VALUE);  //22

        Obj.STATIC_VALUE = 33;

        System.out.println(obj1.STATIC_VALUE);  //33
        System.out.println(obj2.STATIC_VALUE);  //33
    }

    public static class Obj
    {
        public static int STATIC_VALUE = 55;

        public static int getStaticValue()
        {
            return STATIC_VALUE;
        }
    }
}
