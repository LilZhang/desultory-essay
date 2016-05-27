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
 * File Name      : NestingInterfaceMain.java
 */
public class NestingInterfaceMain
{
    public static void main(String[] args)
    {
        NestingInterfaceClass nestingInterfaceClass = new NestingInterfaceClass();

        //没有接口 D 的权限
        //NestingInterfaceClass.D d = nestingInterfaceClass.getD();

        //
        //NestingInterfaceClass.DImpl2 dImpl2 = nestingInterfaceClass.getD();

        //没有接口 D 的权限，也没有 D 的成员的权限
        //nestingInterfaceClass.getD().d();

        NestingInterfaceClass nestingInterfaceClass2 = new NestingInterfaceClass();

        //可以通过这样的方式访问 D
        nestingInterfaceClass2.receiveD(nestingInterfaceClass.getD());
    }

    public class BImpl implements NestingInterfaceClass.B
    {
        public void b()
        {

        }
    }

    class CImpl implements NestingInterfaceClass.C
    {
        public void c()
        {

        }
    }


}
