/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configAnno;

import oops.beanToAop.ReturnedService;
import oops.beanToAop.ThrowableService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-20
 * Project        : desultory-essay
 * File Name      : MainAnno.java
 */
public class MainAnno
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/configAnno/applicationContext.xml");

        ReturnedService returnedService = applicationContext.getBean("returnedService", ReturnedService.class);
        ThrowableService throwableService = applicationContext.getBean("throwableService", ThrowableService.class);

        returnedService.returnMethod("return");

        // [AopAnno - before(aspectj)] method: returnMethod, args: [return], target: oops.beanToAop.ReturnedService
        // [AopAnno - before(aspectj) - 2] method: returnMethod, args: [return], target: oops.beanToAop.ReturnedService
        // [ReturnedService] now invoke ReturnedService.returnMethod(), param: return
        // [AopAnno - around(aspectj) - 2] method: returnMethod, args: [return], target: oops.beanToAop.ReturnedService, duration: 50
        // [AopAnno - around(aspectj)] method: returnMethod, args: [return], target: oops.beanToAop.ReturnedService, duration: 53
        // [AopAnno - after(aspectj)] method: returnMethod, args: [return], target: oops.beanToAop.ReturnedService
        // [AopAnno - after(aspectj) - 2] method: returnMethod, args: [return], target: oops.beanToAop.ReturnedService
        // [AopAnno - afterReturning(aspectj)] method: returnMethod, args: [return], target: oops.beanToAop.ReturnedService

        try
        {
            throwableService.throwMethod("throw");
        }
        catch (Exception e)
        {
            System.out.println("throwableService throw Exception");
        }

        // [AopAnno - before(aspectj)] method: throwMethod, args: [throw], target: oops.beanToAop.ThrowableService
        // [AopAnno - before(aspectj) - 2] method: throwMethod, args: [throw], target: oops.beanToAop.ThrowableService
        // [ThrowableService] now invoke ThrowableService.throwMethod(), param: throw
        // [AopAnno - after(aspectj)] method: throwMethod, args: [throw], target: oops.beanToAop.ThrowableService
        // [AopAnno - after(aspectj) - 2] method: throwMethod, args: [throw], target: oops.beanToAop.ThrowableService
        // [AopAnno - afterThrowing(aspectj)] method: throwMethod, args: [throw], target: oops.beanToAop.ThrowableService, throw: here comes the exception
        // [AopAnno - afterThrowing(aspectj) - 2] method: throwMethod, args: [throw], target: oops.beanToAop.ThrowableService, throw: here comes the exception
        // throwableService throw Exception

    }
}
