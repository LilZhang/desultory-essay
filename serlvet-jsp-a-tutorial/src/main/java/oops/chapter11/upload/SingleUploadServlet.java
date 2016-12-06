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

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-05
 * Project        : desultory-essay
 * File Name      : SingleUploadServlet.java
 */
@WebServlet(urlPatterns = "/single_upload")
@MultipartConfig(               // 处理上传文件必需注解
        maxFileSize = -1L,       // 表示最多可上传文件的容量，超过设定值的文件将被拒绝。默认 -1 表示不受限制
        maxRequestSize = -1L,    // 表示允许多部分HTTP请求的最大容量。默认 -1 表示不受限制
        fileSizeThreshold = 0,  // 设置溢出尺寸，超过这个值的文件将被写入磁盘
        location = "/home/lilzhang/Desktop/upload") // 文件保存位置
public class SingleUploadServlet extends HttpServlet
{
    // 假设上传是由 upload.html 中的 input 标签执行上传的
    // <input type="file" name="input_1"/>
    // 若上传文件为 note.txt，则该 Part 标头如下

    // content-type:text/plain
    // content-disposition:form-data; name="input_1"; filename="note.txt"

    // 若上传文件为空，则该 Part 标头如下,注意 filename 为空字符串

    // content-type:application/octet-stream
    // content-disposition:form-data; name="input_1"; filename=""

    // 如果上传多个文件，则每个文件 (Part) 都会有一个类似的标头

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        boolean success = false;
        String filename;
        long size = 0L;

        Part part = req.getPart("input_1");// ???????

        String header = part.getHeader("content-disposition");
        String[] split = header.split(";");
        String split3 = split[2];
        filename = split3.substring(split3.indexOf("\"") + 1, split3.lastIndexOf("\"")); // note.txt

        if (filename.isEmpty())
        {
            System.out.println("SingleUploadServlet: no files given");
        }
        else
        {
            filename = System.currentTimeMillis() + "-" + filename;
            size = part.getSize();
            part.write(filename);
            success = true;
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
            writer.print("<div>单个文件上传成功。</div>");
            writer.print("<div>文件名: ");
            writer.print(filename);
            writer.print("</div>");
            writer.print("<div>大小: ");
            writer.print(size);
            writer.print("</div>");
            writer.print("</body>");
        }
        else
        {
            writer.print("<body><div>没有选择文件。</div></body>");
        }
        writer.print("</html>");
    }
}
