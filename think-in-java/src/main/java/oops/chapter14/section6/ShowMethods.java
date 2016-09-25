/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter14.section6;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-11
 * Project        : desultory-essay
 * File Name      : ShowMethods.java
 */
public class ShowMethods
{
    public static void main(String[] args)
    {
        InterA interA = new InterAImpl();

        Class<? extends InterA> aClass = interA.getClass();

        Class<?>[] declaredClasses = null;

        try
        {
            Class<?> forName = Class.forName("oops.chapter14.section6.ShowMethods");

            declaredClasses = forName.getDeclaredClasses();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        for (Class<?> clazz : declaredClasses)
        {

            Annotation[] annotations = clazz.getAnnotations();

            Field[] declaredFields = clazz.getDeclaredFields();

            Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

            Method[] declaredMethods = clazz.getDeclaredMethods();

            System.out.println();
        }
    }

    @Deprecated
    protected static class ExampleClass
    {
        private String field;

        @Deprecated
        private void method()
        {

        }
    }

    interface InterA
    {

    }

    protected static class InterAImpl implements InterA
    {

    }
}
