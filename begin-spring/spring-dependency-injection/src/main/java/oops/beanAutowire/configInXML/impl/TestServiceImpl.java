/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.beanAutowire.configInXML.impl;

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
    private TestDao testDaoAutoWiredByType;

    public void setTestDaoAutoWiredByType(TestDao testDaoAutoWiredByType)
    {
        this.testDaoAutoWiredByType = testDaoAutoWiredByType;
    }

    public TestModel handle(TestModel model)
    {
        return this.testDaoAutoWiredByType.gen(model);
    }
}
