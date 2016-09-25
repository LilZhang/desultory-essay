/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter10.section5;

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
    public Destination getDestination(boolean b)
    {
        if (b)
        {
            // public, private and protected not allowed here
            // 该内部类无法拥有静态属性，静态字段以及静态初始化块
            class DestinationImpl implements Destination
            {
                {
                    System.out.println("DestinationImpl init");
                }

                private String label;

                public DestinationImpl(String label)
                {
                    System.out.println("DestinationImpl constructor");
                    this.label = label;
                }

                public String readLabel()
                {
                    return label;
                }
            }

            return new DestinationImpl("test");
        }
        else
        {
            return null;
        }

    }

    public static void main(String[] args)
    {
        Parcel parcel = new Parcel();
        Destination destination = parcel.getDestination(false); // 什么输出也没有
    }
}
