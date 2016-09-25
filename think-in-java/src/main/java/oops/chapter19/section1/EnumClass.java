/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter19.section1;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-16
 * Project        : desultory-essay
 * File Name      : EnumClass.java
 */
public class EnumClass
{
    public static void main(String[] args)
    {
        for (Shrubbery shrubbery : Shrubbery.values())
        {
            System.out.println(shrubbery + " ordinal: " + shrubbery.ordinal());

            System.out.println("compareTo: " + shrubbery.compareTo(Shrubbery.CRAWING));

            System.out.println("equals: " + shrubbery.equals(Shrubbery.CRAWING));

            System.out.println("==: " + (shrubbery == Shrubbery.CRAWING));

            System.out.println(shrubbery.getDeclaringClass());

            System.out.println(shrubbery.name());

            System.out.println();
        }

        for (String s : "GROUND,CRAWING,HANGING".split(","))
        {
            Shrubbery shrubbery = Enum.valueOf(Shrubbery.class, s);

            System.out.println(shrubbery);
        }
    }
//    GROUND ordinal: 0
//    compareTo: -1
//    equals: false
//        ==: false
//    class oops.chapter19.section1.Shrubbery
//        GROUND
//
//    CRAWING ordinal: 1
//    compareTo: 0
//    equals: true
//        ==: true
//    class oops.chapter19.section1.Shrubbery
//        CRAWING
//
//    HANGING ordinal: 2
//    compareTo: 1
//    equals: false
//        ==: false
//    class oops.chapter19.section1.Shrubbery
//        HANGING
//
//    GROUND
//    CRAWING
//    HANGING
}

enum Shrubbery
{
    GROUND,

    CRAWING,

    HANGING
}