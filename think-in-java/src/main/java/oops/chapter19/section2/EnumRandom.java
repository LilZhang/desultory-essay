/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter19.section2;

import java.util.Random;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-16
 * Project        : desultory-essay
 * File Name      : EnumRandom.java
 */
public class EnumRandom
{
    private static Random random = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec)
    {
        return random(ec.getEnumConstants());
    }

    private static <T> T random(T[] values)
    {
        return values[random.nextInt(values.length)];
    }
}
