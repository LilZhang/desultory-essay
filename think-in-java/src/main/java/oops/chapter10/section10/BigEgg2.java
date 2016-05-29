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
 * File Name      : BigEgg2.java
 */
class Egg2
{
    private Yolk y;

    protected class Yolk
    {
        public Yolk()
        {
            System.out.println("Egg2.Yolk()");
        }

        public void f()
        {
            System.out.println("Egg2.Yolk.f()");
        }
    }

    public Egg2()
    {
        System.out.println("new Egg2()");
        setYolk(new Yolk());
    }

    public void setYolk(Yolk y)
    {
        this.y = y;
    }

    public Yolk getYolk()
    {
        return y;
    }
}

public class BigEgg2 extends Egg2
{
    private class Yolk2 extends Egg2.Yolk
    {
        public Yolk2()
        {
            System.out.println("BigEgg2.Yolk2()");
        }

        public void f()
        {
            System.out.println("BigEgg2.Yolk2.f()");
        }
    }

    public BigEgg2()
    {
        System.out.println("new BigEgg2()");
        setYolk(new Yolk2());
    }

    public static void main(String[] args)
    {
        BigEgg2 bigEgg2 = new BigEgg2();
        bigEgg2.getYolk().f();

        /**
         new Egg2()         初始化 BigEgg2 父类
         Egg2.Yolk()        初始化 BigEgg2 父类时调用的 Yolk 构造器
         new BigEgg2()      初始化 BigEgg2
         Egg2.Yolk()        初始化 BigEgg2 时调用的 Yolk2 构造器, 先初始化父类 Egg2.Yolk
         BigEgg2.Yolk2()    初始化 BigEgg2 时调用的 Yolk2 构造器
         BigEgg2.Yolk2.f()
         */
    }
}
