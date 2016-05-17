/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter3.section7;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-05-16
 * Project        : desultory-essay
 * File Name      : TestEquals.java
 */
public class TestEquals
{
    public static void main(String[] args)
    {
        Obj obj1 = new Obj();
        Obj obj2 = new Obj();

        obj1.field = 1;
        obj2.field = 1;

        System.out.println(obj1 == obj2);       // false
        System.out.println(obj1.equals(obj2));  // true

        String str1 = new String("abc");
        String str2 = new String("abc");

        System.out.println(str1 == str2);       // false
        System.out.println(str1.equals(str2));  // true

        String str3 = "def";
        String str4 = "def";
        System.out.println(str3 == str4);       // true
        System.out.println(str3.equals(str4));  // true

        Integer int1 = new Integer(11);
        Integer int2 = new Integer(11);
        System.out.println(int1 == int2);       // false
        System.out.println(int1.equals(int2));  // true

    }

    public static class Obj
    {
        int field;

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (!(o instanceof Obj))
            {
                return false;
            }

            Obj obj = (Obj) o;

            return field == obj.field;

        }

        @Override
        public int hashCode()
        {
            return field;
        }
    }
}
