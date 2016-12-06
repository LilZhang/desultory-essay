/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter11.download;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-05
 * Project        : desultory-essay
 * File Name      : PreDownloadServlet.java
 */
@WebServlet(urlPatterns = "/pre_download")
public class PreDownloadServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        /*String reference = req.getHeader("reference");
        if ("http://localhost:8081/tutorial/pre_download".equals(reference))
        {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/download");
            requestDispatcher.forward(req, resp);
        }
        else
        {
            resp.sendRedirect("my_http_servlet");
        }
        return;*/

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/download");
        requestDispatcher.forward(req, resp);
    }
}
