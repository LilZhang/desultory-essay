/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.request;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-05
 * Project        : desultory-essay
 * File Name      : MyServletRequestAttributeListener.java
 */
@WebListener
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener
{
    public void attributeAdded(ServletRequestAttributeEvent srae)
    {
        System.out.println("某个 ServletRequest 的 " + srae.getName() + "属性被添加");
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae)
    {
        System.out.println("某个 ServletRequest 的 " + srae.getName() + "属性被删除");
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae)
    {
        System.out.println("某个 ServletRequest 的 " + srae.getName() + "属性被替换");
    }
}
