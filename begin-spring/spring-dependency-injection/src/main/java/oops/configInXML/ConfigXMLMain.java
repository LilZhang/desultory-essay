/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configInXML;

import oops.TestModel;
import oops.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-15
 * Project        : desultory-essay
 * File Name      : ConfigXMLMain.java
 */
public class ConfigXMLMain
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/configInXML/applicationContext.xml");

        TestService testService = applicationContext.getBean("testService", TestService.class);
        TestModel model = testService.handle(new TestModel(1));
        System.out.println(model);

        TestModel myTestModel = applicationContext.getBean("myTestModel", TestModel.class);
        System.out.println(myTestModel);

        TestModel constractorTestModel = applicationContext.getBean("constractorTestModel", TestModel.class);
        System.out.println(constractorTestModel);

        TestModel constractorTestModel2 = applicationContext.getBean("constractorTestModel2", TestModel.class);
        System.out.println(constractorTestModel2);

        TestModel factoryTestModel = applicationContext.getBean("factoryTestModel", TestModel.class);
        System.out.println(factoryTestModel);
    }
}
