/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configClass.impl;

import oops.TestDao;
import oops.TestModel;
import oops.TestService;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : TestServiceImpl.java
 */
public class TestServiceImpl implements TestService
{
    private TestDao testDao;

    public TestDao getTestDao()
    {
        return testDao;
    }

    public void setTestDao(TestDao testDao)
    {
        this.testDao = testDao;
    }

    public TestModel handle(TestModel model)
    {
        return this.testDao.gen(model);
    }
}
