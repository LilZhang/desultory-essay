/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9.section9.impl;

import oops.chapter9.section9.Service;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-27
 * Project        : desultory-essay
 * File Name      : ServiceImpl1.java
 */
public class ServiceImpl1 implements Service
{
    private static ServiceImpl1 instance = new ServiceImpl1();

    public static ServiceImpl1 getInstance()
    {
        return instance;
    }

    private ServiceImpl1()
    {

    }

    public void method1()
    {
        System.out.println("ServiceImpl1 method1()");
    }

    public void method2()
    {
        System.out.println("ServiceImpl1 method2()");
    }
}
