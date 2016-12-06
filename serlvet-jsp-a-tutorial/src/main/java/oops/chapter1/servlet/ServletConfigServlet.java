/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter1.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-02
 * Project        : desultory-essay
 * File Name      : ServletConfigServlet.java
 */
@WebServlet(name = "servletConfigServlet", urlPatterns = "/servlet_config",
    initParams = {
            @WebInitParam(name = "key1", value = "value1"),
            @WebInitParam(name = "key2", value = "value2"),
            @WebInitParam(name = "key3", value = "value3")
    })
public class ServletConfigServlet implements Servlet
{
    private transient ServletConfig servletConfig;

    public void init(ServletConfig servletConfig) throws ServletException
    {
        this.servletConfig = servletConfig;
    }

    public ServletConfig getServletConfig()
    {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException
    {
        Map<String, String> configMap = new HashMap<String, String>();
        configMap.put("name", servletConfig.getServletName());
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements())
        {
            String key = initParameterNames.nextElement();
            String initParameter = servletConfig.getInitParameter(key);
            configMap.put(key, initParameter);
        }

        PrintWriter writer = servletResponse.getWriter();
        writer.print(configMap);
    }

    public String getServletInfo()
    {
        return null;
    }

    public void destroy()
    {

    }
}
