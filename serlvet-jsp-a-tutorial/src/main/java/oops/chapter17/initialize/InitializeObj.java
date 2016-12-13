/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.initialize;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-06
 * Project        : desultory-essay
 * File Name      : InitializeObj.java
 */
public class InitializeObj
{
    private String content;

    {
        content = "initialized";
        System.out.println("InitializeObj initialized");
    }
}
