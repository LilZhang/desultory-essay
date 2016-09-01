/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section5.p3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-01
 * Project        : desultory-essay
 * File Name      : Restaurant.java
 */
class Meal
{
    private final int orderNum;

    public Meal(int orderNum)
    {
        this.orderNum = orderNum;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Meal{");
        sb.append("orderNum=").append(orderNum);
        sb.append('}');
        return sb.toString();
    }
}

class WaitPerson implements Runnable
{
    private Restaurant restaurant;

    public WaitPerson(Restaurant restaurant)
    {
        this.restaurant = restaurant;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                synchronized (this)
                {
                    while (restaurant.meal == null)
                    {
                        wait();
                    }
                }
                System.out.println("Waitperson got " + restaurant.meal);
                synchronized (restaurant.chef)
                {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("WaitPerson interrupted");
        }
    }
}

class Chef implements Runnable
{
    private Restaurant restaurant;

    private int count = 0;

    public Chef(Restaurant restaurant)
    {
        this.restaurant = restaurant;
    }

    public void run()
    {
        try
        {
            while (!Thread.interrupted())
            {
                synchronized (this)
                {
                    while (restaurant.meal != null)
                    {
                        wait();
                    }
                }
                if (++count == 10)
                {
                    System.out.println("Out of food, closing...");
                    restaurant.exec.shutdownNow();
                }
                System.out.println("Order up!");
                synchronized (restaurant.waitPerson)
                {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("Chef interrupted");
        }
    }
}

public class Restaurant
{
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);

    public Restaurant()
    {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args)
    {
        new Restaurant();

        /*
        Order up!
        Waitperson got Meal{orderNum=1}
        Order up!
        Waitperson got Meal{orderNum=2}
        Order up!
        Waitperson got Meal{orderNum=3}
        Order up!
        Waitperson got Meal{orderNum=4}
        Order up!
        Waitperson got Meal{orderNum=5}
        Order up!
        Waitperson got Meal{orderNum=6}
        Order up!
        Waitperson got Meal{orderNum=7}
        Order up!
        Waitperson got Meal{orderNum=8}
        Order up!
        Waitperson got Meal{orderNum=9}
        Out of food, closing...
        Order up!
        WaitPerson interrupted
        Chef interrupted
        */
    }
}
