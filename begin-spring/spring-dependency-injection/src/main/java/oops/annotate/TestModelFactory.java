/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.annotate;

import oops.TestModel;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-16
 * Project        : desultory-essay
 * File Name      : TestModelFactory.java
 */
@Component
public class TestModelFactory
{
    @Bean(name = "factoryTestModel")
    public TestModel genModel()
    {
        return new TestModel(222, "由注解工厂类创建。");
    }
}
