/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter5.section6;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-19
 * Project        : desultory-essay
 * File Name      : ClassDefaultValue.java
 */
public class ClassDefaultValue
{
    public static void main(String[] args)
    {
        Obj obj = new Obj();
        System.out.println(obj);
    }

    public static class Obj
    {
        int i = 11;
        String s = "default";
        Obj2 obj2 = new Obj2();

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("Obj{");
            sb.append("i=").append(i);
            sb.append(", s='").append(s).append('\'');
            sb.append(", obj2=").append(obj2);
            sb.append('}');
            return sb.toString();
        }
    }

    public static class Obj2
    {
        int i = 22;
        String s = "default2";

        @Override
        public String toString()
        {
            final StringBuilder sb = new StringBuilder("Obj2{");
            sb.append("i=").append(i);
            sb.append(", s='").append(s).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
