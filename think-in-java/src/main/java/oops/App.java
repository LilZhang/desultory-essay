/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-10
 * Project        : desultory-essay
 * File Name      : App.java
 */
public class App
{
    private static final String PATH = "/home/lilzhang/Desktop/map.txt";

    public static void main(String[] args)
    {
        char c = 1;
        hello(c);
    }

    private static void hello(byte b)
    {
        System.out.println("hello byte");
    }

    private static void hello(short s)
    {
        System.out.println("hello short");
    }

//    private static void hello(char c)
//    {
//        System.out.println("hello char");
//    }

    private static void hello(int i)
    {
        System.out.println("hello int");
    }

    private static void hello(long l)
    {
        System.out.println("hello long");
    }

    private static void hello(float f)
    {
        System.out.println("hello float");
    }

    private static void hello(double d)
    {
        System.out.println("hello double");
    }

    private static void hello(Byte b)
    {
        System.out.println("hello java.lang.Byte");
    }

    private static void hello(Number n)
    {
        System.out.println("hello java.lang.Number");
    }

    private static void hello(Object o)
    {
        System.out.println("hello Object");
    }

    private static void hello(byte... b)
    {
        System.out.println("hello byte...");
    }

    private static void hello(Byte... b)
    {
        System.out.println("hello Byte...");
    }

    private static void hello(Object... o)
    {
        System.out.println("hello Object...");
    }
}