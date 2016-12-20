/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.beanToAop;

import oops.beanToAop.annotation.OneAnnotation;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-20
 * Project        : desultory-essay
 * File Name      : ReturnedService.java
 */
@Component
public class ReturnedService
{
    @OneAnnotation
    public String returnMethod(String param)
    {
        System.out.format("[ReturnedService] now invoke ReturnedService.returnMethod(), param: %s\n", param);
        return param;
    }
}