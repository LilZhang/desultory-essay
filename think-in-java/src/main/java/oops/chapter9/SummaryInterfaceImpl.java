/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-02
 * Project        : desultory-essay
 * File Name      : SummaryInterfaceImpl.java
 */
public class SummaryInterfaceImpl implements SummaryInterface
{
    /**
     * 注释：防懵
     * 类的成员(属性，方法，内部类) 四个权限，外加静态动态通吃
     * 类内部定义的接口，所有权归类本身所有(static) 四个权限都可用
     */

    // 实现时只能限定为public
    public void f()
    {
        System.out.println(SummaryInterface.NAME);
        SummaryInterface.Test test = new SummaryInterface.Test();
        test.testA();
        test.testB();
    }

    // 自动为 static
    interface B
    {
        void b();
    }
}
