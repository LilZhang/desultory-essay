/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.proxy.justInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-07
 * Project        : desultory-essay
 * File Name      : InvocationHandlerImpl.java
 */
public class InvocationHandlerImpl implements InvocationHandler
{
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println(method.getDeclaringClass().getName());
        System.out.println(method.getName());
        return false;
    }
}
