/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.annotate;

import org.springframework.stereotype.Repository;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-16
 * Project        : desultory-essay
 * File Name      : InjectComponent.java
 */

@Repository
public class InjectComponent
{
    public void doComp()
    {
        System.out.println("注入类。");
    }
}
