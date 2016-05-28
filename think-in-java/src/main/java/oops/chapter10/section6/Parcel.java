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
    public Contents getContents()
    {
        return new Contents()
        {
            // 匿名类没有构造器
            // 没有静态属性，静态方法及静态初始化块

            private int value = 666;

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
        parcel.getContents().value();
    }
}
