/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.deprecated.chapter2.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-03
 * Project        : desultory-essay
 * File Name      : SessionBuildServlet.java
 */
@WebServlet(urlPatterns = "/build_session")
public class SessionBuildServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        session.setAttribute("refresh", new MySessionObj(session.getId()));
        session.setMaxInactiveInterval(300);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print("<!DOCTYPE html>");
        writer.print("<html>");
        writer.print("<head><meta http-equiv=\"refresh\" content=\"3; url=/tutorial/refresh_count\" /></head>");
        writer.print("<body><div>登录成功, 页面跳转中...</div></body>");
        writer.print("</html>");
    }
}
