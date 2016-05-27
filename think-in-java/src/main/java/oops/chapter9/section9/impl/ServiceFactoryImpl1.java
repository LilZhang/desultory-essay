/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9.section9.impl;

import oops.chapter9.section9.Service;
import oops.chapter9.section9.ServiceFactory;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-27
 * Project        : desultory-essay
 * File Name      : ServiceFactoryImpl1.java
 */
public class ServiceFactoryImpl1 implements ServiceFactory
{
    private static ServiceFactoryImpl1 instance = new ServiceFactoryImpl1();

    public static ServiceFactoryImpl1 getInstance()
    {
        return instance;
    }

    private ServiceFactoryImpl1()
    {

    }

    public Service getService()
    {
        return ServiceImpl1.getInstance();
    }
}
