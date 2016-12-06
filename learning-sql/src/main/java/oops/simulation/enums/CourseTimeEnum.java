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
 * File Name      : CourseTimeEnum.java
 */
public enum CourseTimeEnum implements Probable<String>
{
    MONDAY_FORENOON("monday_forenoon", 5),

    MONDAY_AFTERNOON("monday_afternoon", 5),

    TUESDAY_FORENOON("tuesday_forenoon", 5),

    TUESDAY_AFTERNOON("tuesday_afternoon", 5),

    WEDNESDAY_FORENOON("wednesday_forenoon", 5),

    WEDNESDAY_AFTERNOON("wednesday_afternoon", 5),

    THURSDAY_FORENOON("thursday_forenoon", 5),

    THURSDAY_AFTERNOON("thursday_afternoon", 5),

    FRIDAY_FORENOON("friday_forenoon", 4),

    FRIDAY_AFTERNOON("friday_afternoon", 3),

    WEEKEND("weekend", 1),

    ;

    private String time;

    private int probability;

    CourseTimeEnum(String time, int probability)
    {
        this.time = time;
        this.probability = probability;
    }

    public String getTime()
    {
        return time;
    }

    public String getValue()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getProbability()
    {
        return probability;
    }

    public void setProbability(int probability)
    {
        this.probability = probability;
    }
}
