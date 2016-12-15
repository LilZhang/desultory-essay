/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configClass;

import oops.TestDao;
import oops.TestService;
import oops.configClass.impl.TestDaoImpl;
import oops.configClass.impl.TestServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : ConfigClass.java
 */
@Configuration  // 若由配置类来配置 bean ，需加此注解
public class ConfigClass
{
    @Bean   // 方法名称就是 bean 的名称
    public TestService testService()
    {
        System.out.println("在此方法中生成bean");
        TestServiceImpl bean = new TestServiceImpl();
        bean.setTestDao(testDao());
        return bean;
    }

    @Bean
    public TestDao testDao()
    {
        System.out.println("在此方法中生成bean");
        TestDaoImpl bean = new TestDaoImpl();
        return bean;
    }
}
