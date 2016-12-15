/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.annotate.impl;

import oops.TestDao;
import oops.TestModel;
import org.springframework.stereotype.Service;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : TestDaoImpl.java
 */

@Service("testDao")
public class TestDaoImpl implements TestDao
{
    public TestModel gen(TestModel model)
    {
        model.setContent("在 @注解 中配置bean。");
        return model;
    }
}
