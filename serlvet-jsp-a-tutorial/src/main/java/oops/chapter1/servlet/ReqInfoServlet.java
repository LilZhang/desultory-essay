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
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-02
 * Project        : desultory-essay
 * File Name      : RequestInfoServlet.java
 */
@WebServlet(urlPatterns = "/req_info")
public class ReqInfoServlet implements Servlet
{
    public void init(ServletConfig servletConfig) throws ServletException
    {

    }

    public ServletConfig getServletConfig()
    {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException
    {
        Map<String, Object> infoMap = new HashMap<String, Object>();
        infoMap.put("请求字节数", servletRequest.getContentLength());
        infoMap.put("请求MIME类型", servletRequest.getContentType());
        infoMap.put("请求参数", servletRequest.getParameterMap());
        infoMap.put("请求协议", servletRequest.getProtocol());

        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("UTF-8");

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head></head><body>");
        sb.append("<div>该HTML显示请求的参数</div>");
        sb.append("<table>");
        for (Map.Entry<String, Object> entry : infoMap.entrySet())
        {
            sb.append("<tr>");
            sb.append("<td>");
            sb.append(entry.getKey());
            sb.append("</td>");
            sb.append("<td>");
            sb.append(entry.getValue());
            sb.append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table></body></html>");

        PrintWriter writer = servletResponse.getWriter();
        writer.print(sb.toString());
    }

    public String getServletInfo()
    {
        return null;
    }

    public void destroy()
    {

    }
}
