/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-03
 * Project        : desultory-essay
 * File Name      : MyServletContextListener.java
 */
@WebListener
public class MyServletContextListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent sce)
    {
        ServletContext servletContext = sce.getServletContext();
        // 在 Tomcat 中为 Tomcat 目录下的 bin 目录
        // 打包后的文件所在地
        String realPath = servletContext.getRealPath("/");
        System.out.println("ServletContext 被初始化。 servletContext.getRealPath(\"/\"): " + realPath);
        servletContext.setAttribute("example_attribute", new Object());
    }

    public void contextDestroyed(ServletContextEvent sce)
    {
        System.out.println("ServletContext 即将被销毁。");
    }
}
