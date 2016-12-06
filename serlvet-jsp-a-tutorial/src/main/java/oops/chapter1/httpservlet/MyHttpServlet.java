/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter1.httpservlet;

import oops.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-03
 * Project        : desultory-essay
 * File Name      : MyHttpServlet.java
 */
@WebServlet(urlPatterns = "/my_http_servlet")
public class MyHttpServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Map<String, String> infoMap = Utils.getReqInfoMap(req);

        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        writer.print("<!DOCTYPE html>");
        writer.print("<html><head></head><body><div>test</div>");
        writer.print("<table>");
        for (Map.Entry<String, String> entry : infoMap.entrySet())
        {
            writer.print("<tr>");
            writer.print("<td>");
            writer.print(entry.getKey());
            writer.print("</td>");
            writer.print("<td>");
            writer.print(entry.getValue());
            writer.print("</td>");
            writer.print("</tr>");
        }
        writer.print("</table></body></html>");

    }
}
