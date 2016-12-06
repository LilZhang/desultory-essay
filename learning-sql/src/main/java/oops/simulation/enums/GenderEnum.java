/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation.enums;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : GenderEnum.java
 */
public enum GenderEnum implements Probable<String>
{
    MALE("male", 60),

    FEMALE("female", 40);

    private String message;

    private int probability;

    GenderEnum(String message, int probability)
    {
        this.message = message;
        this.probability = probability;
    }

    public String getMessage()
    {
        return message;
    }

    public String getValue()
    {
        return message;
    }

    public int getProbability()
    {
        return probability;
    }
}
