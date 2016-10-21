/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.greetAndForget;

import oops.chapter8.greet.Greeter;
import oops.chapter8.greet.classLoaderVer12.GreeterClassLoader;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-18
 * Project        : desultory-essay
 * File Name      : GreetAndForget.java
 */

// 动态装载的类无法触及而被jvm卸载的例子
public class GreetAndForget
{
    // /home/lilzhang/projects/IdeaProjects/desultory-essay/desultory-essay/inside-the-java-virtual-machine/target/classes
    // oops.chapter8.greetAndForget.greeterimpl.Surprise
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("illegal arguments");
            return;
        }

        for (int i = 1; i < args.length; i++)
        {
            try
            {
                GreeterClassLoader gcl = new GreeterClassLoader(args[0]);

                Class<?> clazz = gcl.loadClass(args[i]);

                Object o = clazz.newInstance();

                Greeter greeter = (Greeter) o;

                greeter.greet();

                // ============

                gcl = null;

                clazz = null;

                o = null;

                greeter = null;

                // 这个时候，经过 GreeterClassLoader gcl 装载的类就是不可触及的
                // 该类会被 jvm 卸载

                // GrteerClassLoader 装载了 Surprise
                // Surprise 的常量池中有指向另外四个 Greet 类的入口
                // main() 运行的时候会去解析用到的入口
                // 然后当前类装载器会装载，连接并初始化其中的一个类(假设是HowDoYoDo类)
                // 当然 先由父装载器装载，然后是子装载器，一直到返回 Class 为止
                // 过程中，java.lang.System, java.io.PrintStream 由启动类装载器装载(被委派到父类启动器装载)
                // Surprise 和 之后 new 出来的 HowDoYoDo 是由 GrteerClassLoader 装载的( GrteerClassLoader 为其定义类装载器)
                // 然后类和实例都能被访问到

                // 1. 对象可以访问到该类的类数据
                // 2. 类数据能访问到该类的 Class 对象
                // 3. classloader 能通过一个 HashTable 访问到类的 Class 对象

                // 执行完赋 null 操作后
                // Surprise 类及其Class 对象无法被触及
                // 作为其常量池入口，HowDoYoDo 及其 class对象 也无法被触及
                // 对应的实例也无法被触及
                // 类将被卸载
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
