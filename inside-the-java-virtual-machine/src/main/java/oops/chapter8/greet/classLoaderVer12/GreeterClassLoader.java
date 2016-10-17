/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.greet.classLoaderVer12;

import java.io.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-17
 * Project        : desultory-essay
 * File Name      : GreeterClassLoader.java
 */
    // loadClass() 基本工作方式：
    // 1. 给定需要查找的类型的全限定名
    // 2. 找到或生成字节数组byte[]     // 否则 ClassNotFoundException
    // 3. 将byte[]传给 defineClass()，装载至这个类装载器的 namespace 下
    // 在 1.2 中可指定保护域
    // 装载成功，返回 Class 对象

    // detail see loadClass_ClassLoader.md

    // 使用1.2版本的jvm的用户自定义类装载器
public class GreeterClassLoader extends ClassLoader
{
    // findClass() 的步骤与上方的 loadClass() 基本相似

    private String basePath;

    public GreeterClassLoader(String basePath)
    {
        this.basePath = basePath;
    }

    public GreeterClassLoader(ClassLoader parent, String basePath)
    {
        super(parent);
        this.basePath = basePath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        byte[] classData;

        // 字节码
        classData = getTypeFromBasePath(name);
        if (classData == null)
        {
            throw new ClassNotFoundException();
        }

        // 装载类
        // 收录至当前类装载器的 namespace 下
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] getTypeFromBasePath(String typeName)
    {
        byte[] result = null;
        FileInputStream fis = null;
        ByteArrayOutputStream out = null;
        String fileName = genFileName(typeName);

        try
        {
            fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
            out = new ByteArrayOutputStream();

            int buffer = 0;
            while ((buffer = bis.read()) != -1)
            {
                out.write(buffer);
            }
            result =  out.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }

            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }

        return result;
    }

    private String genFileName(String typeName)
    {
        StringBuilder sb = new StringBuilder(basePath);
        sb.append(File.separatorChar);
        sb.append(typeName.replace('.', File.separatorChar));
        sb.append(".class");
        return sb.toString();
    }
}
