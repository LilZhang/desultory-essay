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
 * File Name      : MajorDurationEnum.java
 */
public enum MajorDurationEnum implements Probable<Integer>
{
    THREE_YEAR(3, 14),

    FOUR_YEAR(4, 80),

    FIVE_YEAR(5, 6),

    ;

    private int duration;

    private int probability;

    MajorDurationEnum(int duration, int probability)
    {
        this.duration = duration;
        this.probability = probability;
    }

    public int getDuration()
    {
        return duration;
    }

    public Integer getValue()
    {
        return duration;
    }

    public int getProbability()
    {
        return probability;
    }
}
