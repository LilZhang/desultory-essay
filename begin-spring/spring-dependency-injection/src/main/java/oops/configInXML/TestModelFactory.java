/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configInXML;

import oops.TestModel;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-16
 * Project        : desultory-essay
 * File Name      : TestModelFactory.java
 */
public class TestModelFactory
{
    public TestModel genModel()
    {
        return new TestModel(333, "由 XML 工厂类创建。");
    }
}
