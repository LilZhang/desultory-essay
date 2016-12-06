/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-04
 * Project        : desultory-essay
 * File Name      : MySessionAttributeListener.java
 */
@WebListener
public class MySessionAttributeListener implements HttpSessionAttributeListener
{
    public void attributeAdded(HttpSessionBindingEvent event)
    {
        System.out.println("session JSESSIONID "
                + event.getSession().getId()
                + "的 " + event.getName() + "属性被添加");
    }

    public void attributeRemoved(HttpSessionBindingEvent event)
    {
        System.out.println("session JSESSIONID "
                + event.getSession().getId()
                + "的 " + event.getName() + "属性被删除");
    }

    public void attributeReplaced(HttpSessionBindingEvent event)
    {
        System.out.println("session JSESSIONID "
                + event.getSession().getId()
                + "的 " + event.getName() + "属性被替换");
    }
}
