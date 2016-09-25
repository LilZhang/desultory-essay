/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-17
 * Project        : desultory-essay
 * File Name      : App1.java
 */
public class App1
{
    public static void main(String[] args)
    {
        Directions directions = Directions.NULL;

        switch (directions)
        {
            case EAST:
                System.out.println();
                break;
            case WEST:
                System.out.println();
                break;
            case NORTH:
                System.out.println();
                break;
            case SOUTH:
                System.out.println();
                break;
            case NULL:
                System.out.println();
                break;
            default:
                System.out.println();
        }
    }

    public static <T extends Enum<T>> T[] operateEnum(Class<T> clazz)
    {
        return clazz.getEnumConstants();
    }
}

enum Directions
{

    EAST(1, "east")
            {
                @Override
                public void info()
                {

                }
            },

    WEST(2, "west")
            {
                @Override
                public void info()
                {

                }
            },

    NORTH(3, "north")
            {
                @Override
                public void info()
                {

                }
            },

    SOUTH(4, "south")
            {
                @Override
                public void info()
                {

                }
            },

    NULL(5, "null")
            {
                @Override
                public void info()
                {

                }
            };

    private int type;

    private String message;

    Directions(int type, String message)
    {
        this.type = type;
        this.message = message;
    }

    public static Directions get(int value)
    {
        Directions result = Directions.NULL;
        for (Directions direction : Directions.values())
        {
            if (value == direction.getType())
            {
                result = direction;
                break;
            }
        }
        return result;
    }

    public abstract void info();

    public int getType()
    {
        return type;
    }

    public String getMessage()
    {
        return message;
    }
}