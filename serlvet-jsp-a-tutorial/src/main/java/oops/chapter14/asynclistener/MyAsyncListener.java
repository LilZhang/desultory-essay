/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter14.asynclistener;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-06
 * Project        : desultory-essay
 * File Name      : MyAsyncListener.java
 */

// 无注解, 需要手动添加
public class MyAsyncListener implements AsyncListener
{
    public void onComplete(AsyncEvent event) throws IOException
    {
        System.out.println("异步操作完成");
    }

    public void onTimeout(AsyncEvent event) throws IOException
    {
        System.out.println("异步操作超时");
    }

    public void onError(AsyncEvent event) throws IOException
    {
        System.out.println("异步操作出错");
    }

    public void onStartAsync(AsyncEvent event) throws IOException
    {
        System.out.println("异步操作启动");
    }
}
