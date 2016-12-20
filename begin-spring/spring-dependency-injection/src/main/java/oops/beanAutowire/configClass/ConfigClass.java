/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.beanAutowire.configClass;

import oops.TestDao;
import oops.TestService;
import oops.beanAutowire.configClass.impl.TestDaoImpl;
import oops.beanAutowire.configClass.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-16
 * Project        : desultory-essay
 * File Name      : ConfigClass.java
 */
@Configuration
public class ConfigClass
{
    @Bean(autowire = Autowire.BY_NAME)
    public TestService testService()
    {
        return new TestServiceImpl();
    }

    @Bean
    public TestDao testDaoAutoWiredByName()
    {
        System.out.println("通过 bean name 注入。");
        return new TestDaoImpl();
    }
}
