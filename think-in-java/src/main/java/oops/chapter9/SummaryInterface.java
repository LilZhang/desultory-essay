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
 * File Name      : SummaryInterface.java
 */
public interface SummaryInterface
{
    /**
     * 在接口中，所有成员(属性，方法，内部类，内部接口)的权限都为public
     * 除了方法待其他类实现外，
     * 其余成员皆为接口所有(static)
     */

    // 即 public + static + final
    String NAME = "name";

    // 即 public 待实现
    // 实现时只能限定为public
    void f();

    // 即 public + static
    interface I
    {
        void i();
    }

    // 即 public + static
    class Test
    {
        void testA()
        {

        }

        void testB()
        {

        }
    }
}
