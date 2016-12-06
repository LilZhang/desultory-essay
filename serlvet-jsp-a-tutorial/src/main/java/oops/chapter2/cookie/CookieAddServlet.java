/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter2.cookie;

import oops.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-03
 * Project        : desultory-essay
 * File Name      : CookieServlet.java
 */
@WebServlet(urlPatterns = "/add_cookie")
public class CookieAddServlet extends HttpServlet
{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int cookieAlive = 300;
        Date date = new Date(System.currentTimeMillis() +
                TimeUnit.MILLISECONDS.convert(cookieAlive, TimeUnit.SECONDS));
        Cookie cookie = new Cookie("login", Utils.randomString(10) + "," + sdf.format(date));
        cookie.setMaxAge(cookieAlive);
        resp.addCookie(cookie);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print("<!DOCTYPE html>");
        writer.print("<html>");
        writer.print("<head><meta http-equiv=\"refresh\" content=\"3; url=/tutorial/login\" /></head>");
        writer.print("<body><div>登录成功, 页面跳转中...</div></body>");
        writer.print("</html>");
    }
}
