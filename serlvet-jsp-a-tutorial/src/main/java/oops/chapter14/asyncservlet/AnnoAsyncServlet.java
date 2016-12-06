/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter14.asyncservlet;

import oops.chapter14.asynclistener.MyAsyncListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-06
 * Project        : desultory-essay
 * File Name      : AsyncServlet.java
 */
@WebServlet(asyncSupported = true, urlPatterns = "/anno_async")
public class AnnoAsyncServlet extends HttpServlet
{
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        final AsyncContext asyncContext = req.startAsync();   // 多次调用返回同一个 asyncContext
        asyncContext.setTimeout(60000);
        asyncContext.addListener(new MyAsyncListener());
        asyncContext.start(new Runnable()
        {
            public void run()
            {
                try
                {
                    TimeUnit.SECONDS.sleep(3);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                int i = (int) (Math.random() * 2);  // 0 or 1
                if (i == 0)
                {
                    asyncContext.dispatch("/my_http_servlet");
                }
                else
                {
                    try
                    {
                        resp.setContentType("text/html");
                        resp.setCharacterEncoding("UTF-8");
                        PrintWriter writer = resp.getWriter();
                        writer.print("<!DOCTYPE html>");
                        writer.print("<html>");
                        writer.print("<head></head>");
                        writer.print("<body><div>异步操作结束</div></body>");
                        writer.print("</html>");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    asyncContext.complete();
                }
            }
        });
    }
}
