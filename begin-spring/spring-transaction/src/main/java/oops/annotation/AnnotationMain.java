/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-13
 * Project        : desultory-essay
 * File Name      : AnnotationMain.java
 */
public class AnnotationMain
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(
                        "/config/annotation/applicationContext.xml");

        UserDemoManager userDemoManager = applicationContext.getBean(UserDemoManager.class);
        userDemoManager.transferAge(12, 25, 2);
    }
}
