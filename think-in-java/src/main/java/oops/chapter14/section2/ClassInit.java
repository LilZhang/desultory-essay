/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter14.section2;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-06
 * Project        : desultory-essay
 * File Name      : ClassInit.java
 */
public class ClassInit
{
    public static void main(String[] args)
    {
        try
        {
            // 类会被初始化
            Class<?> aClass = Class.forName("oops.chapter14.section2.InitA");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // 类不会被初始化
        Class<InitB> bClass = InitB.class;
    }
}

class InitA
{
    static
    {
        System.out.println("init InitA");
    }
}

class InitB
{
    static
    {
        System.out.println("init InitB");
    }
}
