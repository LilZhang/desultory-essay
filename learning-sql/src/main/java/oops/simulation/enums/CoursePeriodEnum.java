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
 * File Name      : CoursePeriodEnum.java
 */
public enum CoursePeriodEnum implements Probable<String>
{
    TUTORING("tutoring", 65),

    MID_TERM("mid_term", 15),

    EXAMINE("examine", 20),

    ;

    private String period;

    private int probability;

    CoursePeriodEnum(String period, int probability)
    {
        this.period = period;
        this.probability = probability;
    }

    public String getPeriod()
    {
        return period;
    }

    public String getValue()
    {
        return period;
    }

    public int getProbability()
    {
        return probability;
    }
}
