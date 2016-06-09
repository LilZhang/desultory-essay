/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter14.section2;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-06
 * Project        : desultory-essay
 * File Name      : ClassSuper.java
 */
public class ClassSuper
{
    public static void main(String[] args)
    {
        Class<FactoryToy> factoryToyClass = FactoryToy.class;

        try
        {
            FactoryToy factoryToy = factoryToyClass.newInstance();
        }
        catch (IllegalAccessException iae)
        {
            iae.printStackTrace();
        }
        catch (InstantiationException ie)
        {
            ie.printStackTrace();
        }

        Class<? super FactoryToy> factoryToyClassSuperclass =
                factoryToyClass.getSuperclass();

        // won't compile
        // Class<Toy> factoryToyClassSuperclass =
        //      factoryToyClass.getSuperclass();


        Toy t = new FactoryToy();
        factoryToyClass.cast(t);

        Class<Toy> toyClass = Toy.class;
        toyClass.isAssignableFrom(factoryToyClass);     // true
        toyClass.isAssignableFrom(Integer.class);       // false

        System.out.println();
    }
}

class Toy
{

}

class FactoryToy extends Toy
{

}