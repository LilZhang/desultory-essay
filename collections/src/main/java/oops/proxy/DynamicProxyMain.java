/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.proxy;

import java.lang.reflect.Proxy;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-24
 * Project        : desultory-essay
 * File Name      : DynamicProxyMain.java
 */
public class DynamicProxyMain
{
    public static void main(String[] args)
    {
        ExecutableObj executableObj = new ExecutableObj();
        ProxyHandler proxyHandler = new ProxyHandler(executableObj);
        Executable executable = ((Executable) Proxy.newProxyInstance(ExecutableObj.class.getClassLoader(),
                ExecutableObj.class.getInterfaces(),
                proxyHandler));

        executable.execute();
    }
}
