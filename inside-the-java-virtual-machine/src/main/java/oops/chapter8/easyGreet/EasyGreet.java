/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.easyGreet;

import oops.chapter8.greet.Greeter;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-18
 * Project        : desultory-essay
 * File Name      : EasyGreeet.java
 */


    // Class.forName() 与 loadClass() 的最大不同之处
    // loadClass() 是用自定义类装载器，将类装载到自己的 namespace
    // Class.forName() 装载类至当前类装载器中，当前 namespace

public class EasyGreet
{
    // oops.chapter8.greet.greeterimpl.Hello
    // oops.chapter8.greet.greeterimpl.Greetings
    // oops.chapter8.greet.greeterimpl.Salutation
    // oops.chapter8.greet.greeterimpl.HowDoYoDo
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("illegal arguments");
            return;
        }

        for (int i = 0; i < args.length; i++)
        {
            try
            {
                Class<?> clazz = Class.forName(args[i]);

                Object o = clazz.newInstance();

                Greeter greeter = (Greeter) o;

                greeter.greet();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}
