/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.deprecated.chapter2.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-03
 * Project        : desultory-essay
 * File Name      : LoginServlet.java
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        boolean login = false;
        String value = "";
        String due = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals("login"))
                {
                    login = true;
                    String[] split = cookie.getValue().split(",");
                    value = split[0];
                    due = split[1];
                }
            }
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        if (login)
        {
            writer.print(alreadyLogin(value, due));
        }
        else
        {
            writer.print(needLogin());
        }
    }

    private String needLogin()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div>请登录</div>");
        sb.append("<div><a href=\"/tutorial/add_cookie\">点击登录</a></div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    private String alreadyLogin(String value, String due)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div>您已登录</div>");
        sb.append("<div>检测到cookie: ");
        sb.append(value);
        sb.append(", 5分钟内登录状态有效</div>");
        sb.append("<div>");
        sb.append("登录有效期至: ");
        sb.append(due);
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
