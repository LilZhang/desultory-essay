/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section10;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-18
 * Project        : desultory-essay
 * File Name      : GenericReading.java
 */
public class GenericReading
{
    static <T> T readExact(List<T> list)
    {
        return list.get(0);
    }

    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruits = Arrays.asList(new Fruit());

    static void f1()
    {
        Apple a = readExact(apples);
        Fruit f = readExact(fruits);
        f = readExact(apples);
    }

    static class Reader<T>
    {
        T readExact(List<T> list)
        {
            return list.get(0);
        }
    }

    static void f2()
    {
        Reader<Fruit> fruitReader = new Reader<Fruit>();
        Fruit f = fruitReader.readExact(fruits);
//        Fruit a = fruitReader.readExact(apples);  //ERROR
        // need: Fruit; now: Apple
    }

    static class CovarianReader<T>
    {
        T readExact(List<? extends T> list)
        {
            return list.get(0);
        }
    }

    static void f3()
    {
        CovarianReader<Fruit> fruitCovarianReader = new CovarianReader<Fruit>();
        Fruit f = fruitCovarianReader.readExact(fruits);
        Fruit a = fruitCovarianReader.readExact(apples);
    }
}
