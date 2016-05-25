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
