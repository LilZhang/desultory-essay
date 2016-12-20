/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.beanAutowire.configClass.impl;

import oops.TestDao;
import oops.TestModel;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : TestDaoImpl.java
 */
public class TestDaoImpl implements TestDao
{
    public TestModel gen(TestModel model)
    {
        model.setContent("由 config class 自动装填。");
        return model;
    }
}
