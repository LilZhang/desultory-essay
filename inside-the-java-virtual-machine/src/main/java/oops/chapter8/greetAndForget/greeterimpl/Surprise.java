/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter8.greetAndForget.greeterimpl;

import oops.chapter8.greet.Greeter;
import oops.chapter8.greet.greeterimpl.Greetings;
import oops.chapter8.greet.greeterimpl.Hello;
import oops.chapter8.greet.greeterimpl.HowDoYoDo;
import oops.chapter8.greet.greeterimpl.Salutation;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-18
 * Project        : desultory-essay
 * File Name      : Surprise.java
 */
public class Surprise implements Greeter
{
    public void greet()
    {
        int choice = ((int) (Math.random() * 3.99));

        Greeter g;

        switch (choice)
        {
            case 0:
                g = new Hello();
                g.greet();
                break;

            case 1:
                g = new Greetings();
                g.greet();
                break;

            case 2:
                g = new Salutation();
                g.greet();
                break;

            case 3:
                g = new HowDoYoDo();
                g.greet();
                break;
        }
    }
}
