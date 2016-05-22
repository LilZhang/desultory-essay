/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.section2;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-22
 * Project        : desultory-essay
 * File Name      : Binding.java
 */
public class Binding
{
    public static void main(String[] args)
    {

        /*
        1. 前期绑定(静态绑定，编译时绑定)：static方法，final(private)方法
        2. 后期绑定(动态绑定，运行时绑定)：其余皆为后期绑定
        */

        Obj obj = new Obj();
        outputField(obj);           // outputField : base field
        outputStaticMethod(obj);    // outputStaticMethod : base static method
        outputPrivateMethod(obj);   // outputPrivateMethod : base private method
        outputPublicMethod(obj);    // outputPublicMethod : obj public method
    }

    private static void outputField(Base base)
    {
        System.out.println("outputField : " + base.field);
    }

    private static void outputStaticMethod(Base base)
    {
        System.out.println("outputStaticMethod : " + base.staticMethod());
    }

    private static void outputPrivateMethod(Base base)
    {
        System.out.println("outputPrivateMethod : " + base.privateMethod());
    }

    private static void outputPublicMethod(Base base)
    {
        System.out.println("outputPublicMethod : " + base.publicMethod());
    }

    public static class Base
    {
        private String field = "base field";

        public static String staticMethod()
        {
            return "base static method";
        }

        public String publicMethod()
        {
            return "base public method";
        }

        private String privateMethod()
        {
            return "base private method";
        }
    }

    public static class Obj extends Base
    {
        private String field = "obj field";

        public static String staticMethod()
        {
            return "obj static method";
        }

        @Override
        public String publicMethod()
        {
            return "obj public method";
        }

        private String privateMethod()
        {
            return "obj private method";
        }
    }
}
