/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.proxy.justInterface;

import java.lang.reflect.Proxy;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-07
 * Project        : desultory-essay
 * File Name      : MapperMain.java
 */
public class MapperMain
{
    public static void main(String[] args)
    {
        TestMapper testMapper = ((TestMapper) Proxy.newProxyInstance(TestMapper.class.getClassLoader(),
                new Class<?>[]{TestMapper.class},
                new InvocationHandlerImpl()));

        testMapper.selectSth();
    }
}
