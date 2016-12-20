/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configAnno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-20
 * Project        : desultory-essay
 * File Name      : AopAnno.java
 */
@Component
@Aspect
public class AopAnno
{
    @Before("execution(public * *(..))")
    public void methodForBefore(JoinPoint jp)
    {

    }

    @AfterReturning("execution(public * *(..))")
    public void methodForAfterReturnning(JoinPoint jp)
    {

    }

    @AfterThrowing(value = "execution(public * *(..))", throwing = "t")
    public void methodForAfterThrowing(JoinPoint jp, Throwable t)
    {

    }

    @After("execution(public * *(..))")
    public void methodForAfter(JoinPoint jp)
    {

    }

    @Around("execution(public * *(..))")
    public void methodForAround(JoinPoint jp) throws Throwable
    {

    }


    // ============================================
    // 以下两个方法仅用来定义切面 (Pointcut)
    // 所以不需要返回类型(void)
    // 方法体也不需要
    // 方法名被用来作为别的 AOP 的配置项之一，如下
    // 配置项之间可 &, |, &&, || 来拼接切入点

    @Pointcut("@annotation(oops.beanToAop.annotation.OneAnnotation)")
    public void anyMethodWithOneAnno()
    {

    }

    @Pointcut("@annotation(oops.beanToAop.annotation.AnotherAnnotation)")
    public void anyMethodWithAnotherAnno()
    {

    }

    // ============================================

    @Before("anyMethodWithOneAnno()")
    public void methodForBefore2(JoinPoint jp)
    {

    }

    @AfterReturning("anyMethodWithAnotherAnno()")
    public void methodForAfterReturnning2(JoinPoint jp)
    {

    }

    @AfterThrowing(value = "anyMethodWithOneAnno() && anyMethodWithAnotherAnno()", throwing = "t")
    public void methodForAfterThrowing2(JoinPoint jp, Throwable t)
    {

    }

    @After("anyMethodWithOneAnno()")
    public void methodForAfter2(JoinPoint jp)
    {

    }

    @Around("anyMethodWithOneAnno() || anyMethodWithAnotherAnno()")
    public void methodForAround2(JoinPoint jp) throws Throwable
    {

    }
}
