/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.greet.classLoaderVer11;

import java.io.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-17
 * Project        : desultory-essay
 * File Name      : GreeterClassLoader.java
 */


    // 使用1.1版本的jvm的用户自定义类装载器
    // 必须创建 ClassLoader 的子类
    // 并且实现 Class<?> loadClass(String name, boolean resolve) 方法
    //
public class GreeterClassLoader extends ClassLoader
{
    // 目录路径
    private String basePath;

    public GreeterClassLoader(String basePath)
    {
        this.basePath = basePath;
    }



    // loadClass() 基本工作方式：
    // 1. 给定需要查找的类型的全限定名
    // 2. 找到或生成字节数组byte[]     // 否则 ClassNotFoundException
    // 3. 将byte[]传给 defineClass()，装载至这个类装载器的 namespace 下
    // 在 1.2 中可指定保护域
    // 装载成功，返回 Class 对象

    // detail see loadClass_ClassLoader.md

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException
    {
        Class<?> result;
        byte[] classData;

        // 检查类型是否已被装载
        // 多次调用 findLoadedClass(name) 并使用同一个name
        // 返回同一个Class<?>
        result = findLoadedClass(name);
        if (result != null)
        {
            return result;
        }

        // 原始的类装载器的装载尝试
        // 在1.2中是系统类装载器
        try
        {
            result = findSystemClass(name);
            return result;
        }
        catch (ClassNotFoundException e)
        {
            // ?
        }

        // 防止标准的java包被除了启动类之外的其它类装载
        if (name.startsWith("java."))
        {
            throw new ClassNotFoundException();
        }

        // 获取字节码
        classData = getTypeFromBasePath(name);
        if (classData == null)
        {
            throw new ClassNotFoundException("GCL - Can't load class" + name);
        }

        // 装载字节码，并获取Class
        // 可在 defineClass 最后一个参数传入 ProtectionDomain
        // 也就是保护域指定这个Class的所属保护域
        result = defineClass(name, classData, 0, classData.length);
        if (result == null)
        {
            throw new ClassFormatError("GCL - class format error" + name);
        }

        // resolve 为 true 则连接该类
        if (resolve)
        {
            resolveClass(result);
        }
        return result;
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
