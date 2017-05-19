/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-24
 * Project        : desultory-essay
 * File Name      : ProxyHandler.java
 */
public class ProxyHandler implements InvocationHandler
{
    private Object base;

    public ProxyHandler(Object base)
    {
        this.base = base;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object result = null;
        String proxyName = proxy.getClass().getName();
        String methodName = method.getName();
        String arg = Arrays.toString(args);
        System.out.println("proxy: " + proxyName);
        System.out.println("method: " + methodName);
        System.out.println("args: " + arg);
        System.out.println("dynamic proxy before invoke");
        result = method.invoke(base, args);
        System.out.println("dynamic proxy after invoke");
        return result;
    }
}
