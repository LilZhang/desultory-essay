/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.greet.greeterimpl;

import oops.chapter8.greet.Greeter;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-17
 * Project        : desultory-essay
 * File Name      : HowDoYoDo.java
 */
public class HowDoYoDo implements Greeter
{
    public void greet()
    {
        System.out.println("How do yo do, globe!");
    }
}
