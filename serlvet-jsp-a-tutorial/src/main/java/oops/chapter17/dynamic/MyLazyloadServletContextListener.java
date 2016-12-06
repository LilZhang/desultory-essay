/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.dynamic;

import oops.chapter17.dynamic.lazyload.LazyloadFilter;
import oops.chapter17.dynamic.lazyload.LazyloadListener;
import oops.chapter17.dynamic.lazyload.LazyloadServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.util.EnumSet;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-06
 * Project        : desultory-essay
 * File Name      : MyLazyloadServletContextListener.java
 */
@WebListener
public class MyLazyloadServletContextListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent sce)
    {
        System.out.println("MyLazyloadServletContextListener 正在初始化一些组件");

        ServletContext servletContext = sce.getServletContext();

        servletContext.addListener(LazyloadListener.class);

        FilterRegistration.Dynamic filterDynamic = servletContext.addFilter("lazyloadFilter", LazyloadFilter.class);
        filterDynamic.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/lazyload");

        ServletRegistration.Dynamic servletDynamic = servletContext.addServlet("lazyloadServlet", LazyloadServlet.class);
        servletDynamic.addMapping("/lazyload");

        System.out.println("MyLazyloadServletContextListener 初始化完成");
    }

    public void contextDestroyed(ServletContextEvent sce)
    {

    }
}
