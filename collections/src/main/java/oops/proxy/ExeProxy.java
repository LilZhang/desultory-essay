/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.proxy;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-16
 * Project        : desultory-essay
 * File Name      : ExeProxy.java
 */
public class ExeProxy implements Executable
{
    private Executable executable;

    public ExeProxy(Executable executable)
    {
        this.executable = executable;
    }

    public void execute()
    {
        System.out.println("proxy start");
        executable.execute();
        System.out.println("proxy ternimate");
    }
}
