/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.dynamic.lazyload;

import javax.servlet.*;
import java.io.IOException;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-06
 * Project        : desultory-essay
 * File Name      : LazyloadFilter.java
 */
public class LazyloadFilter implements Filter
{
    public void init(FilterConfig filterConfig) throws ServletException
    {
        System.out.println("LazyloadFilter 初始化");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        System.out.println("LazyloadFilter 执行过滤");
        chain.doFilter(request, response);
    }

    public void destroy()
    {

    }
}
