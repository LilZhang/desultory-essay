/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.session;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-04
 * Project        : desultory-essay
 * File Name      : MySessionObj3.java
 */
public class MySessionBindingObj implements HttpSessionBindingListener
{
    public void valueBound(HttpSessionBindingEvent event)
    {
        System.out.println("该对象被 session JSESSIONID " + event.getSession().getId()
                + " 绑定到" + event.getName() + "属性");
        // 可以自动更新
    }

    public void valueUnbound(HttpSessionBindingEvent event)
    {
        System.out.println("该对象被 session JSESSIONID " + event.getSession().getId()
                + " 的" + event.getName() + "属性解绑");
        // 可以释放资源
    }
}
