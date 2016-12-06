/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.servletcontext;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-03
 * Project        : desultory-essay
 * File Name      : MyServletContextAttributeListener.java
 */
public class MyServletContextAttributeListener implements ServletContextAttributeListener
{
    String exampleAttribute = "example_attribute";

    public void attributeAdded(ServletContextAttributeEvent event)
    {
        System.out.println("ServletContext 中的某个属性被添加。");
        if (event.getName().equals(exampleAttribute))
        {

        }
    }

    public void attributeRemoved(ServletContextAttributeEvent event)
    {
        System.out.println("ServletContext 中的某个属性被删除。");
    }

    public void attributeReplaced(ServletContextAttributeEvent event)
    {
        System.out.println("ServletContext 中的某个属性被修改。");
    }
}
