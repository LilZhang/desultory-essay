/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter2.session;

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
 * File Name      : RefreshCountServlet.java
 */
@WebServlet(urlPatterns = "/refresh_count")
public class RefreshCountServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        MySessionObj refresh = (MySessionObj) session.getAttribute("refresh");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        if (refresh != null)
        {
            int i = refresh.increaseCount();
            writer.print(alreadyBuilt(session.getId(), i));
        }
        else
        {
            writer.print(needBuild());
        }
    }

    private String needBuild()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div>请参与活动</div>");
        sb.append("<div><a href=\"/tutorial/build_session\">点击参与</a></div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    private String alreadyBuilt(String value, int count)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div>活动</div>");
        sb.append("<div><a href=\"/tutorial/refresh_count\">REFREEEEEEESH !!</a></div>");
        sb.append("<div>JSESSIONID: ");
        sb.append(value);
        sb.append(", 无操作 5 分钟后状态失效</div>");
        sb.append("<div>");
        sb.append("刷新数: ");
        sb.append(count);
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
