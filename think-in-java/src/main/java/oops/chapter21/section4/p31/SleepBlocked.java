/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section4.p31;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-28
 * Project        : desultory-essay
 * File Name      : SleepBlocked.java
 */
public class SleepBlocked implements Runnable
{
    // 可被中断
    // 理论上只要能抛出 InterruptedException 异常的 都可以被中断
    public void run()
    {
        try
        {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch (InterruptedException e)
        {
//            e.printStackTrace();
            System.out.println("InterruptedException of SleepBlocked");
        }
        System.out.println("exiting SleepBlocked.run()");
    }
}
