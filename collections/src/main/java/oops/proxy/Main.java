/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.proxy;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-16
 * Project        : desultory-essay
 * File Name      : Main.java
 */
public class Main
{
    public static void main(String[] args)
    {
        new ExeProxy(new ExecutableObj()).execute();
    }
}
