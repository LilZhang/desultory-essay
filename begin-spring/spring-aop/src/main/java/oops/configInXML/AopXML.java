/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.configInXML;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-20
 * Project        : desultory-essay
 * File Name      : AopXML.java
 */
public class AopXML implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice
{
    public void before(Method method, Object[] args, Object target) throws Throwable
    {
        System.out.format("[AopXML - before] method: %s, args: %s, target: %s\n",
                method.getName(), Arrays.toString(args), target);
    }

    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable
    {
        System.out.format("[AopXML - afterReturning] method: %s, args: %s, target: %s, returnValue: %s\n",
                method.getName(), Arrays.toString(args), target, returnValue);
    }

    // 这个方法在 ThrowsAdvice 中没有，需要自己手动添加
    // 若在这个方法中抛出异常，将会覆盖原异常
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex)
    {
        System.out.format("[AopXML - afterThrowing] method: %s, args: %s, target: %s, exception: %s\n",
                method.getName(), Arrays.toString(args), target, ex.getMessage());
    }

    // ==========================================================================

    // 不管返回值或抛出异常 都会执行
    // XML 中设置方法略有不同
    public void methodForAfter(JoinPoint jp)
    {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        String args = Arrays.toString(jp.getArgs());

        System.out.format("[AopXML - after(aspectj)] method: %s, args: %s, target: %s\n",
                methodName, args, className);
    }

    public void statExeTimeUsingAround(ProceedingJoinPoint jp) throws Throwable
    {
        long start = System.currentTimeMillis();

        String targetName = jp.getTarget().getClass().getCanonicalName();
        String methodName = jp.getSignature().getName();
        String args = Arrays.toString(jp.getArgs());

        jp.proceed();

        long duration = System.currentTimeMillis() - start;
        System.out.format("[AopXML - around(aspectj)] method: %s, args: %s, target: %s, duration: %d\n",
                methodName, args, targetName, duration);
    }
}
