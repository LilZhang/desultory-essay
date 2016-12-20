/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.beanAutowire.configInXML;

import oops.TestModel;
import oops.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-16
 * Project        : desultory-essay
 * File Name      : Main.java
 */
public class Main
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/autowire/applicationContext.xml");

        TestService testService = applicationContext.getBean("testService", TestService.class);
        TestModel model = testService.handle(new TestModel(1));

        System.out.println(model);
    }
}
