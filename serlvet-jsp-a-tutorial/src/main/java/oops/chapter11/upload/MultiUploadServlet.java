/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter11.upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-05
 * Project        : desultory-essay
 * File Name      : MultiUploadServlet.java
 */
@WebServlet(urlPatterns = "/multi_upload")
@MultipartConfig(location = "/home/lilzhang/Desktop/upload")
public class MultiUploadServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        boolean success = false;
        Collection<Part> parts = req.getParts();
        int size = parts.size();

        List<String> filenames = new ArrayList<String>(size);
        List<String> sizes = new ArrayList<String>(size);

        for (Part part : parts)
        {
            if (part.getContentType() != null)
            {
                String contentDisposition = part.getHeader("content-disposition");
                String[] split = contentDisposition.split(";");
                String split3 = split[2];
                String filename = split3.substring(split3.indexOf("\"") + 1, split3.lastIndexOf("\""));
                if (!filename.isEmpty())
                {
                    success = true;
                    filename = System.currentTimeMillis() + "-" + filename;
                    filenames.add(filename);
                    sizes.add(String.valueOf(part.getSize()));
                    part.write(filename);
                }
                else
                {
                    break;
                }
            }
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.print("<!DOCTYPE html>");
        writer.print("<html>");
        writer.print("<head></head>");
        if (success)
        {
            writer.print("<body>");
            writer.print("<div>多个文件上传成功。</div>");
            for (int i = 0; i < size; i++)
            {
                writer.print("<div>文件名: ");
                writer.print(filenames.get(i));
                writer.print("</div>");
                writer.print("<div>大小: ");
                writer.print(sizes.get(i));
                writer.print("</div>");
                writer.print("<br>");
            }

            writer.print("</body>");
        }
        else
        {
            writer.print("<body><div>没有选择文件。</div></body>");
        }
        writer.print("</html>");

    }
}
