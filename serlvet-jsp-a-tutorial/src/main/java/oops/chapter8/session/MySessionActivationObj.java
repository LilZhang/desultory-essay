/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.session;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-04
 * Project        : desultory-essay
 * File Name      : MySessionObj2.java
 */
public class MySessionActivationObj implements HttpSessionActivationListener
{
    public void sessionWillPassivate(HttpSessionEvent se)
    {
        System.out.println("session JSESSIONID: " + se.getSession().getId()
                + "即将被钝化(迁移或序列化到文件中)");
    }

    public void sessionDidActivate(HttpSessionEvent se)
    {
        System.out.println("session JSESSIONID: " + se.getSession().getId()
                + "即将被激活");
    }
}
