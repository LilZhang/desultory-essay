/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-05
 * Project        : desultory-essay
 * File Name      : FilterXML.java
 */
public class FilterXML implements Filter
{
    private Map<String, String> configMap = new HashMap<String, String>();

    public void init(FilterConfig filterConfig) throws ServletException
    {
        Enumeration<String> names = filterConfig.getInitParameterNames();
        while (names.hasMoreElements())
        {
            String key = names.nextElement();
            String value = filterConfig.getInitParameter(key);
            configMap.put(key, value);
        }
        System.out.println("Filter FilterXML 初始化。参数: " + configMap);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        System.out.println("Filter FilterXML 执行过滤。参数: " + configMap);
        chain.doFilter(request, response);  // 重要: 不能遗漏
    }

    public void destroy()
    {
        System.out.println("Filter FilterXML 即将被销毁。参数: " + configMap);
    }
}
