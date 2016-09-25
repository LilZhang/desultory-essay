/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter7.section7;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-22
 * Project        : desultory-essay
 * File Name      : Upcasting.java
 */
public class Upcasting
{
    public static void main(String[] args)
    {
        Obj obj = new Obj();
        Obj2 obj2 = new Obj2();
        obj.getInfo();
        obj2.getInfo();
    }

    public static class Base
    {
        public String info()
        {
            return "base info";
        }

        public void getInfo()
        {
            System.out.println(this.info());
        }
    }

    public static class Obj extends Base
    {
        @Override
        public String info()
        {
            return "obj info";
        }
    }

    public static class Obj2 extends Base
    {

    }
}
