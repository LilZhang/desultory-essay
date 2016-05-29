/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter10.section9;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-29
 * Project        : desultory-essay
 * File Name      : InheritInner.java
 */

// 继承内部类
public class InheritInner extends WithInner.Inner
{
    // 继承内部类所必须的构造器
    public InheritInner(WithInner withInner)
    {
        withInner.super();
    }

    public static void main(String[] args)
    {
        WithInner withInner = new WithInner();
        InheritInner inheritInner = new InheritInner(withInner);
    }
}

class WithInner
{
    class Inner
    {

    }
}