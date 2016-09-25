/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter10.section6;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-28
 * Project        : desultory-essay
 * File Name      : Parcel.java
 */
public class Parcel
{
    public Contents getContents(final int val)  // 匿名内部类所使用的外部参数需final
    {
        // 匿名内部类所使用的外部参数需final
        final int val2 = 2;

        return new Contents()
        {
            // 匿名内部类没有构造器
            // 没有静态属性，静态方法及静态初始化块
            // 匿名内部类所使用的外部参数需final

            private int value = val + val2;

            public int value()
            {
                System.out.println("invoke value()");
                return value;
            }
        };
    }

    public static void main(String[] args)
    {
        Parcel parcel = new Parcel();
        parcel.getContents(3).value();
    }
}
