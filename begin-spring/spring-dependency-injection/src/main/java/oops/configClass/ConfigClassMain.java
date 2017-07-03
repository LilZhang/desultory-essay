/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configClass;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : ConfigClassMain.java
 */
public class ConfigClassMain
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ConfigClass.class);
//        TestService testService = applicationContext.getBean("testService", TestService.class);
//        TestModel model = testService.handle(new TestModel(1));

//        System.out.println(model);
    }
}
