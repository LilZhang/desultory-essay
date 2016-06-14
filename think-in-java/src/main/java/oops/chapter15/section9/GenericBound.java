/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section9;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-14
 * Project        : desultory-essay
 * File Name      : GenericBound.java
 */
public class GenericBound
{
    public static <T extends Colorful & Powerful> int method(T obj)
    {
        return obj.getColor() + obj.getPower();
    }
}

interface Colorful
{
    int getColor();
}

interface Powerful
{
    int getPower();
}
