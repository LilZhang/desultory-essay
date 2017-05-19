/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configAnno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String args = Arrays.toString(jp.getArgs());

        System.out.format("[AopAnno - before(aspectj)] method: %s, args: %s, target: %s\n",
                methodName, args, className);
    }

    @AfterReturning("execution(public * *(..))")
    public void methodForAfterReturnning(JoinPoint jp)
    {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String args = Arrays.toString(jp.getArgs());

        System.out.format("[AopAnno - afterReturning(aspectj)] method: %s, args: %s, target: %s\n",
                methodName, args, className);
    }

    @AfterThrowing(value = "execution(public * *(..))", throwing = "t")
    public void methodForAfterThrowing(JoinPoint jp, Throwable t)
    {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String args = Arrays.toString(jp.getArgs());

        System.out.format("[AopAnno - afterThrowing(aspectj)] method: %s, args: %s, target: %s, throw: %s\n",
                methodName, args, className, t.getMessage());
    }

    @After("execution(public * *(..))")
    public void methodForAfter(JoinPoint jp)
    {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String args = Arrays.toString(jp.getArgs());

        System.out.format("[AopAnno - after(aspectj)] method: %s, args: %s, target: %s\n",
                methodName, args, className);
    }

    @Around("execution(public * *(..))")
    public void methodForAround(ProceedingJoinPoint jp) throws Throwable
    {
        long start = System.currentTimeMillis();

        String targetName = jp.getTarget().getClass().getCanonicalName();
        String methodName = jp.getSignature().getName();
        String args = Arrays.toString(jp.getArgs());

        jp.proceed();

        long duration = System.currentTimeMillis() - start;
        System.out.format("[AopAnno - around(aspectj)] method: %s, args: %s, target: %s, duration: %d\n",
                methodName, args, targetName, duration);
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
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String args = Arrays.toString(jp.getArgs());

        System.out.format("[AopAnno - before(aspectj) - 2] method: %s, args: %s, target: %s\n",
                methodName, args, className);
    }

    @AfterReturning("anyMethodWithAnotherAnno()")
    public void methodForAfterReturnning2(JoinPoint jp)
    {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String args = Arrays.toString(jp.getArgs());

        System.out.format("[AopAnno - afterReturning(aspectj) - 2] method: %s, args: %s, target: %s\n",
                methodName, args, className);
    }

    @AfterThrowing(value = "anyMethodWithOneAnno() && anyMethodWithAnotherAnno()", throwing = "t")
    public void methodForAfterThrowing2(JoinPoint jp, Throwable t)
    {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String args = Arrays.toString(jp.getArgs());

        System.out.format("[AopAnno - afterThrowing(aspectj) - 2] method: %s, args: %s, target: %s, throw: %s\n",
                methodName, args, className, t.getMessage());
    }

    @After("anyMethodWithOneAnno()")
    public void methodForAfter2(JoinPoint jp)
    {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String args = Arrays.toString(jp.getArgs());

        System.out.format("[AopAnno - after(aspectj) - 2] method: %s, args: %s, target: %s\n",
                methodName, args, className);
    }

    @Around("anyMethodWithOneAnno() || anyMethodWithAnotherAnno()")
    public void methodForAround2(ProceedingJoinPoint jp) throws Throwable
    {
        long start = System.currentTimeMillis();

        String targetName = jp.getTarget().getClass().getCanonicalName();
        String methodName = jp.getSignature().getName();
        String args = Arrays.toString(jp.getArgs());

        jp.proceed();

        long duration = System.currentTimeMillis() - start;
        System.out.format("[AopAnno - around(aspectj) - 2] method: %s, args: %s, target: %s, duration: %d\n",
                methodName, args, targetName, duration);
    }
}
