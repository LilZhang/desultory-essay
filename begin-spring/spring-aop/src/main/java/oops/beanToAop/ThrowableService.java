/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.beanToAop;

import oops.beanToAop.annotation.AnotherAnnotation;
import oops.beanToAop.annotation.OneAnnotation;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-20
 * Project        : desultory-essay
 * File Name      : ThrowableService.java
 */
@Component
public class ThrowableService
{
    @OneAnnotation
    @AnotherAnnotation
    public void throwMethod(String param) throws Exception
    {
        System.out.format("[ThrowableService] now invoke ThrowableService.throwMethod(), param: %s\n", param);
        throw new Exception("here comes the exception");
    }
}
