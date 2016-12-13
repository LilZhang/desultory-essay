/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    private static final String PATH = "oops.zanno.obj";

    public static void main(String[] args)
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(PATH.replace('.', '/'));
        System.out.println(url.getPath());
        String file = url.getFile();
        File[] classFiles = new File(file).listFiles(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                if (name.endsWith(".class"))
                {
                    return true;
                }
                return false;
            }
        });

        List<Class<?>> classList = new ArrayList<Class<?>>(classFiles.length);
        for (File classFile : classFiles)
        {
            try
            {
                String classFileName = classFile.getName();
                String className = classFileName.substring(0, classFileName.lastIndexOf('.'));
                Class<?> aClass = Class.forName(PATH + "." + className);
                classList.add(aClass);
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        for (Class<?> aClass : classList)
        {
            Method[] methods = aClass.getMethods();
            for (Method method : methods)
            {
                Demo demo = method.getAnnotation(Demo.class);
                if (demo != null)
                {
                    try
                    {
                        Object object = aClass.newInstance();
                        method.invoke(object);
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InstantiationException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
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
