/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.deprecated.chapter2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-03
 * Project        : desultory-essay
 * File Name      : FormServlet.java
 */
@WebServlet(urlPatterns = "/form_servlet")
public class FormServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        String hidden = req.getParameter("hidden");

        Map<String, String> infoMap = new HashMap<String, String>();
        infoMap.put("id", id);
        infoMap.put("name", name);
        infoMap.put("type", type);
        infoMap.put("hidden", hidden);

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
