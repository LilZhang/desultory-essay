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
 * Create Date    : 2016-05-29
 * Project        : desultory-essay
 * File Name      : Parcel3.java
 */
public class Parcel3
{
    public Base getBase(int val)
    {
        return new Base(val)
        {
            @Override
            public int value()
            {
                return super.getValue() + 1;
            }
        };
    }

    public static void main(String[] args)
    {
        System.out.println(new Parcel3().getBase(4).value());
    }
}
