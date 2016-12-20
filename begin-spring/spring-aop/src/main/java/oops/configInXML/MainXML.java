/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configInXML;

import oops.beanToAop.ReturnedService;
import oops.beanToAop.ThrowableService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-20
 * Project        : desultory-essay
 * File Name      : MainXML.java
 */
public class MainXML
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/configInXML/applicationContext.xml");

        ReturnedService returnedService = applicationContext.getBean(ReturnedService.class);
        ThrowableService throwableService = applicationContext.getBean(ThrowableService.class);

        returnedService.returnMethod("return");

        // [AopXML - before] method: returnMethod, args: [return], target: oops.beanToAop.ReturnedService@36dd768d
        // [ReturnedService] now invoke ReturnedService.returnMethod(), param: return
        // [AopXML - after(aspectj)] method: returnMethod, args: [return], target: oops.beanToAop.ReturnedService
        // [AopXML - afterReturning] method: returnMethod, args: [return], target: oops.beanToAop.ReturnedService@36dd768d, returnValue: return

        try
        {
            throwableService.throwMethod("throw");
        }
        catch (Exception e)
        {
            System.out.println("throwableService throw Exception");
        }

        // [AopXML - before] method: throwMethod, args: [throw], target: oops.beanToAop.ThrowableService@451d9bcc
        // [ThrowableService] now invoke ThrowableService.throwMethod(), param: throw
        // [AopXML - after(aspectj)] method: throwMethod, args: [throw], target: oops.beanToAop.ThrowableService
        // [AopXML - afterThrowing] method: throwMethod, args: [throw], target: oops.beanToAop.ThrowableService@451d9bcc, exception: here comes the exception
        // throwableService throw Exception
    }
}
