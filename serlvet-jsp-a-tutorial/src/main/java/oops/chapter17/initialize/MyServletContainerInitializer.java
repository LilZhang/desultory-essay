/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.initialize;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-06
 * Project        : desultory-essay
 * File Name      : MyServletContainerInitializer.java
 */
@HandlesTypes(InitializeObj.class)
public class MyServletContainerInitializer implements ServletContainerInitializer
{

    // not work ??
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException
    {
        System.out.println("servlet 容器初始化方法");
    }
}
