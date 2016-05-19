/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter5.section7;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-20
 * Project        : desultory-essay
 * File Name      : ClassInit.java
 */
public class ClassInit
{
    public static void main(String[] args)
    {
        Obj obj = new Obj(null);

        /* output:
        1. init static code
        2. init field object : static-2
        3. init field object : static
        4. superclass constructor
        5. init object code
        6. init field object : field
        7. init field object : field-2
        8. constructor with params
        */

        /* 总结:
        1. 静态代码块与静态字段初始化同优先级，按顺序初始化
        2. 父类构造器
        3. 代码块与字段初始化同优先级，按顺序初始化
        4. (被调用的)构造器
        */
    }

    public static class Base
    {
        public Base()
        {
            PrintUtil.println("superclass constructor");
        }
    }

    public static class Obj extends Base
    {
        static
        {
            PrintUtil.println("init static code");
        }

        {
            PrintUtil.println("init object code");
        }

        private static Field staticField2 = new Field("static-2");

        private static Field staticField = new Field("static");

        private Field field = new Field("field");

        private Field field2 = new Field("field-2");

        public Obj()
        {
            PrintUtil.println("default constructor");
        }

        public Obj(Field field)
        {
            PrintUtil.println("constructor with params");
            this.field = field;
        }
    }

    public static class Field
    {
        public Field(String str)
        {
            PrintUtil.println("init field object : " + str);
        }
    }

    public static class PrintUtil
    {
        private static int count = 0;

        public static void println(String str)
        {
            System.out.println(++count + ". " + str);
        }
    }
}
