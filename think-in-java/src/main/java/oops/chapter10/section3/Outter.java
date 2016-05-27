/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter10.section3;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-27
 * Project        : desultory-essay
 * File Name      : Outter.java
 */
public class Outter
{
    class Inner
    {
        // 在内部类方法中获取外部类对象引用可以使用 .this
        Outter getOutter()
        {
            return Outter.this;
        }
    }

    public static void main(String[] args)
    {
        Outter.Inner inner = new Outter().new Inner();
    }
}
