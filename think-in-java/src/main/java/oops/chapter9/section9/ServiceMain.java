/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9.section9;

import oops.chapter9.section9.impl.ServiceFactoryImpl1;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-27
 * Project        : desultory-essay
 * File Name      : ServiceMain.java
 */
public class ServiceMain
{
    public static void main(String[] args)
    {
        serviceConsumer(ServiceFactoryImpl1.getInstance());
    }

    public static void serviceConsumer(ServiceFactory factory)
    {
        Service service = factory.getService();
        service.method1();
        service.method2();
    }
}
