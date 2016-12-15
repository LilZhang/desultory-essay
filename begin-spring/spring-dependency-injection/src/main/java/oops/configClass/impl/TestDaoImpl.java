/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configClass.impl;

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
        model.setContent("在配置class中配置bean。");
        return model;
    }
}
