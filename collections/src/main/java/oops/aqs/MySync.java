/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.aqs;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-29
 * Project        : desultory-essay
 * File Name      : MySync.java
 */
public interface MySync
{
    void acquire();

    void release();
}
