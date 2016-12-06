/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-04
 * Project        : desultory-essay
 * File Name      : MySessionListener.java
 */
@WebListener
public class MySessionListener implements HttpSessionListener
{
    public void sessionCreated(HttpSessionEvent se)
    {
        System.out.println("某个 session 被创建, JSESSSIONID: " + se.getSession().getId());

    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
        System.out.println("某个 session 被销毁, JSESSSIONID: " + se.getSession().getId());
    }
}
