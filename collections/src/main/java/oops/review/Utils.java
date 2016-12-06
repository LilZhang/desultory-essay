/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.review;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-01
 * Project        : desultory-essay
 * File Name      : MyCollectionUtils.java
 */
class Utils
{
    protected static final int CEILING = Integer.MAX_VALUE - 8;

    private static final int BOUND = 0;

    protected static final boolean validLength(int len)
    {
        if ((len < BOUND || len > CEILING))
        {
            throw new IllegalArgumentException("illegal length: " + len);
        }
        return true;
    }

    protected static final boolean validIndex(int index)
    {
        if ((index < BOUND || index >= CEILING))
        {
            throw new IllegalArgumentException("illegal index: " + index);
        }
        return true;
    }

    protected static final boolean validIndexForAdd(int index)
    {
        if ((index < BOUND || index >= CEILING))
        {
            throw new IllegalArgumentException("illegal index: " + index);
        }
        return true;
    }
}
