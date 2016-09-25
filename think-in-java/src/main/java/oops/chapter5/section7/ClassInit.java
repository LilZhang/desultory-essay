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
        1. [init] <static> code block @ superclass
        2. [init] <static> staticSuperField @ superclass
        3. [init] <static> staticField2 @ class
        4. [init] <static> code block @ class
        5. [init] <static> staticField @ class
        6. [init] field @ superclass
        7. [init] code block @ superclass
        8. [construct] default constructor @ superclass
        9. [construct] field @ superclass
        10. [init] field @ class
        11. [init] code block @ class
        12. [init] field2 @ class
        13. [construct] constructor with param @ class
        14. [construct] field2 @ class
        15. [construct] field @ class
        */

        /* 总结:
        0. 字段默认0或null
        1. 先静态(类)，后对象
        2. 先父类，后子类
        3. 先初始化(字段初始化与初始化块)，后构造器
        4. 字段初始化与初始化块优先级相同，按顺序初始化
        */
    }

    public static class Base
    {
        static
        {
            PrintUtil.println("[init] <static> code block @ superclass");
        }

        private static Field staticSuperField = new Field("[init] <static> staticSuperField @ superclass");

        private Field field = new Field("[init] field @ superclass");

        {
            PrintUtil.println("[init] code block @ superclass");
        }

        public Base()
        {
            PrintUtil.println("[construct] default constructor @ superclass");
            field = new Field("[construct] field @ superclass");
        }
    }

    public static class Obj extends Base
    {
        private static Field staticField2 = new Field("[init] <static> staticField2 @ class");

        static
        {
            PrintUtil.println("[init] <static> code block @ class");
        }

        private Field field = new Field("[init] field @ class");

        {
            PrintUtil.println("[init] code block @ class");
        }

        private static Field staticField = new Field("[init] <static> staticField @ class");

        private Field field2 = new Field("[init] field2 @ class");

        public Obj()
        {
            PrintUtil.println("[construct] default constructor @ class");
        }

        public Obj(Field field)
        {
            PrintUtil.println("[construct] constructor with param @ class");
            this.field2 = new Field("[construct] field2 @ class");

            PrintUtil.println("[construct] field @ class");
            this.field = field;
        }
    }

    public static class Field
    {
        public Field(String str)
        {
            PrintUtil.println(str);
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
