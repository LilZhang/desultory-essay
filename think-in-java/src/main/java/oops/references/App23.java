/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-27
 * Project        : desultory-essay
 * File Name      : App23.java
 */
public class App23
{
    public static void main(String[] args)
    {
        try
        {
            Class<?> clazz = Class.forName("oops.references.App23");
            Object o = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (Method method : methods)
            {
                Demo annotation = method.getAnnotation(Demo.class);
                if (annotation != null)
                {
                    method.invoke(o);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Demo(params = {"111", "222"})
    public void method()
    {
        System.out.println("do something");
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Demo
    {
        int count() default 0;

        char[] chars() default {'a', 'b'};

        String[] params() default {};
    }
}
