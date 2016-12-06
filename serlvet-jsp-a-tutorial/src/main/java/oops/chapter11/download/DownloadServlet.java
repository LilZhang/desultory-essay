/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter11.download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-05
 * Project        : desultory-essay
 * File Name      : DownloadServlet.java
 */
@WebServlet(urlPatterns = "/download")
public class DownloadServlet extends HttpServlet
{
    private static final String PATH = "/home/lilzhang/Desktop/BytecodeViewer.2.9.8.zip";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        FileInputStream in = null;

        try
        {
            in = new FileInputStream(PATH);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);

            resp.setContentType("application/octet-stream");
            resp.addHeader("Content-Disposition", "attachment;filename=wtf.zip");
            ServletOutputStream out = resp.getOutputStream();
            out.write(bytes);
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
        }
    }
}
