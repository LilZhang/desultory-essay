/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter1.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-02
 * Project        : desultory-essay
 * File Name      : MyServlet.java
 */

@WebServlet(urlPatterns = {"/my", "/test"})
public class MyServlet implements Servlet
{
    // 第一次请求 Servlet 时会调用 init() 方法，传入 ServletConfig 实例
    // 一般将其作为成员变量，供其它方法使用
    private transient ServletConfig servletConfig;

    private final AtomicInteger reqCount = new AtomicInteger(0);

    public void init(ServletConfig servletConfig) throws ServletException
    {
        this.servletConfig = servletConfig;
        System.out.println("init(): 第一次请求 Servlet 时执行 init(), 用以初始化");
    }

    public ServletConfig getServletConfig()
    {
        System.out.println("getServletConfig(): 用以获取 servletConfig");
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException
    {
        System.out.println("service(): 第" + reqCount.incrementAndGet() + "次访问。每一次请求 Servlet 时执行 service(), 所有用户将共用一个 Servlet 实例，注意线程安全。");
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head></head><body>");
        sb.append("这是被构造的HTML");
        sb.append("</body></html>");

        PrintWriter writer = servletResponse.getWriter();
        writer.print(sb.toString());
    }

    public String getServletInfo()
    {
        System.out.println("getServletInfo(): 用以获取 ServletInfo");
        return null;
    }

    public void destroy()
    {
        System.out.println("destroy(): Servlet 被卸载或者容器关闭时执行, 用以清理资源");
    }
}
