/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.zanno.obj;

import oops.zanno.Exe;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-07
 * Project        : desultory-essay
 * File Name      : ExeObj2.java
 */
public class ExeObj2
{
    @Exe
    public void doIt()
    {
        String name = getClass().getName();
        System.out.println("ok: " + name);
    }
}
