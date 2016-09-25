/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section10;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-15
 * Project        : desultory-essay
 * File Name      : Holder.java
 */
public class Holder<T>
{
    private T value;

    public Holder()
    {
    }

    public Holder(T value)
    {
        this.value = value;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj)
    {
        return value.equals(obj);
    }

    public static void main(String[] args)
    {
        Holder<Apple> appleHolder = new Holder<Apple>(new Apple());
        Apple apple = appleHolder.getValue();
        appleHolder.setValue(apple);

        // Holder<Apple> 向上转型至 Holder<? extends Fruit>
        Holder<? extends Fruit> fruitHolder = appleHolder;  // OK
        Fruit fruit = fruitHolder.getValue();
        apple = (Apple) fruitHolder.getValue();

        try
        {
            Orange orange = ((Orange) fruitHolder.getValue());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

//        won't compile
//        fruitHolder.setValue(new Apple());
//        fruitHolder.setValue(new Fruit());

        System.out.println(fruitHolder.equals(apple));
    }
}

class Fruit
{

}

class Apple extends Fruit
{

}

class Orange extends Fruit
{

}

class Jonathan extends Apple
{

}