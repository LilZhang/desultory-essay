/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9.section3;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-24
 * Project        : desultory-essay
 * File Name      : Inter.java
 */
public class Inter
{
    public static void main(String[] args)
    {
        Upcase upcase = new Upcase();
        System.out.println(upcase.getClassName());
    }
}

class Processor
{
    public String getClassName()
    {
        return getClass().getSimpleName();
    }

    public Object process(Object input)
    {
        return input;
    }
}

class Upcase extends Processor
{
    @Override
    public String process(Object input)
    {
        return input.toString().toUpperCase();
    }
}
