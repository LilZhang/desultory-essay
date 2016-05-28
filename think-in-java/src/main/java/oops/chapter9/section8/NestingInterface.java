/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter9.section8;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-26
 * Project        : desultory-essay
 * File Name      : NestingInterface.java
 */
public interface NestingInterface
{
    // 自动为 public
    interface I
    {
        void i();
    }

    // 自动为 public
    public interface J
    {
        void j();
    }

    // private 不能存在于接口中
    /*private interface K
    {

    }*/

    void g();

    // 自动为 public, static
    class NestingInterfaceImpl implements NestingInterface
    {
        public void g()
        {

        }
    }
}
