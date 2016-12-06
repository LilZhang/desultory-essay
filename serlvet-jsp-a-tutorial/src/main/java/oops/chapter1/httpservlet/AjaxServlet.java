/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter1.httpservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-03
 * Project        : desultory-essay
 * File Name      : AjaxServlet.java
 */
public class AjaxServlet extends HttpServlet
{
    @Override
    public void init() throws ServletException
    {
        System.out.println("AjaxServlet: 早加载");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String data = req.getParameter("data");
        StringBuilder sb = new StringBuilder();
        sb.append("{\"msg\":\"success\", \"data\":\"");
        sb.append(data);
        sb.append("\"}");

        resp.setContentType("application/json");
        resp.getWriter().print(sb.toString());
    }
}
