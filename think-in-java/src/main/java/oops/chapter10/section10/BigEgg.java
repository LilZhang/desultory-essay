/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter10.section10;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-29
 * Project        : desultory-essay
 * File Name      : BigEgg.java
 */
class Egg
{
    private Yolk y;

    protected class Yolk
    {
        public Yolk()
        {
            System.out.println("Egg.Yolk()");
        }
    }

    public Egg()
    {
        System.out.println("new Egg()");
        this.y = new Yolk();
    }
}

public class BigEgg extends Egg
{
    public class Yolk
    {
        public Yolk()
        {
            System.out.println("BigEgg.Yolk()");
        }
    }

    public static void main(String[] args)
    {
        // 子类的内部类独立于父类的内部类

        BigEgg bigEgg = new BigEgg();
        /**
         * output:
         *  new Egg()
         *  Egg.Yolk()
         */
    }
}
