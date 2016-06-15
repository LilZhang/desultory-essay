/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-15
 * Project        : desultory-essay
 * File Name      : GenericsAndCovariance.java
 */
public class GenericsAndCovariance
{
    public static void main(String[] args)
    {
        List<? extends Fruit> fruits = new ArrayList<Apple>();

        List<? extends Fruit> fruits2 = Arrays.asList(
                new Apple(), new Orange(), new Fruit()
        );

//        won't compile
//        fruits.add(new Apple());
//        fruits.add(new Orange());
//        fruits.add(new Fruit());

        Fruit fruit = fruits.get(0);
    }

    private static class Fruit
    {

    }

    private static class Apple extends Fruit
    {

    }

    private static class Orange extends Fruit
    {

    }
}
