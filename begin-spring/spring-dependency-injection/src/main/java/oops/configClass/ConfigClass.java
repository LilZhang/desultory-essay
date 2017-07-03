/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configClass;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : ConfigClass.java
 */
@Configuration  // 若由配置类来配置 bean ，需加此注解
@EnableScheduling
@ComponentScan(basePackages = "oops.configClass.scheduled")
public class ConfigClass implements SchedulingConfigurer
{
    /*@Bean   // 方法名称就是 bean 的名称
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
    }*/

    public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
    {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor()
    {
        return Executors.newScheduledThreadPool(10);
    }
}
