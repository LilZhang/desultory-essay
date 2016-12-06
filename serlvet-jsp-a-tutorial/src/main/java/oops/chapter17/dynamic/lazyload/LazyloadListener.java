/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.dynamic.lazyload;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-06
 * Project        : desultory-essay
 * File Name      : LazyloadListener.java
 */
public class LazyloadListener implements ServletRequestListener
{
    public void requestDestroyed(ServletRequestEvent sre)
    {

    }

    public void requestInitialized(ServletRequestEvent sre)
    {
        System.out.println("LazyloadListener 初始化");
    }
}
