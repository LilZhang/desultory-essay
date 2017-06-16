/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.essential.chapter5.section9;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-05-20
 * Project        : desultory-essay
 * File Name      : SeasonEnum.java
 */
public enum SeasonEnum
{
    SPRING(0, "spring"),

    SUMMER(1, "summer"),

    AUTUMN(2, "autumn"),

    WINTER(3, "winter");

    private int value;

    private String message;

    SeasonEnum(int value, String message)
    {
        this.value = value;
        this.message = message;
    }

    public int getValue()
    {
        return value;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("SeasonEnum{");
        sb.append("value=").append(value);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static SeasonEnum get(int seasonValue)
    {
        for (SeasonEnum seasonEnum : SeasonEnum.values())
        {
            if (seasonEnum.getValue() == seasonValue)
            {
                return seasonEnum;
            }
        }
        return null;
    }

    public static void main(String[] args)
    {
        SeasonEnum seasonEnum = SeasonEnum.SPRING;
        switch (seasonEnum)
        {
            case SPRING:
                System.out.println("spring");
                break;
            case SUMMER:
                System.out.println("summer");
                break;
            case AUTUMN:
                System.out.println("autumn");
                break;
            case WINTER:
                System.out.println("winter");
                break;
            default:
                System.out.println("?");
        }
    }
}
