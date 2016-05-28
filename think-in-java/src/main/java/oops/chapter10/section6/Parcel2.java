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
 * Create Author  : Administrator
 * Create Date    : 2016-05-28
 * Project        : desultory-essay
 * File Name      : Parcel2.java
 */
public class Parcel2
{
    // 使用匿名内部类外的变量需final(类属性除外)
    private static int factor2 = 2;

    public static Wrapping getWrapping(int val)
    {
        // 使用匿名内部类外的变量需final(类属性除外)
        final int factor = 3;

        return new Wrapping(val)
        {
            // 该匿名类为Wrapping类的子类，所以需super
            // 调用父类带参数构造器

            @Override
            public int value()
            {
                // 使用匿名内部类外的变量需final(类属性除外)
                return super.value() * factor * factor2;
            }
        };
    }

    public static void main(String[] args)
    {
        System.out.println(getWrapping(6).value());
    }
}
