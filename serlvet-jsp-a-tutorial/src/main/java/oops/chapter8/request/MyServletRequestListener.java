/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.request;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-05
 * Project        : desultory-essay
 * File Name      : MyServletRequestListener.java
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener
{
    public void requestDestroyed(ServletRequestEvent sre)
    {
        System.out.println("某个 ServletRequest 即将被销毁");
    }

    public void requestInitialized(ServletRequestEvent sre)
    {
        System.out.println("某个 ServletRequest 被初始化");
        ServletRequest servletRequest = sre.getServletRequest();
        ServletContext servletContext = sre.getServletContext();
    }
}
