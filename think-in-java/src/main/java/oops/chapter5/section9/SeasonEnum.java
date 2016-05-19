/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter5.section9;

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
}
