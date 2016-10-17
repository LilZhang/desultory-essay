/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.greet;

import oops.chapter8.greet.classLoaderVer11.GreeterClassLoader;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-17
 * Project        : desultory-essay
 * File Name      : GreetMain.java
 */
public class GreetMain11
{
    // /home/lilzhang/projects/IdeaProjects/desultory-essay/desultory-essay/inside-the-java-virtual-machine/target/classes
    // oops.chapter8.greet.greeterimpl.Hello
    // oops.chapter8.greet.greeterimpl.Greetings
    // oops.chapter8.greet.greeterimpl.Salutation
    // oops.chapter8.greet.greeterimpl.HowDoYoDo
    public static void main(String[] args)
    {
        if (args.length <= 1)
        {
            System.out.println("illegal arguments");
            return;
        }

        GreeterClassLoader gcl = new GreeterClassLoader(args[0]);

        for (int i = 1; i < args.length; i++)
        {
            try
            {
                Class<?> clazz = gcl.loadClass(args[i]);
                Object o = clazz.newInstance();
                Greeter greeter = (Greeter) o;
                greeter.greet();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
