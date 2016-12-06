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
 * Create Date    : 2016-11-25
 * Project        : desultory-essay
 * File Name      : StudentCourseScoreEnum.java
 */
public enum StudentCourseScoreEnum implements Probable<String>
{
    A("A", 15),

    B("B", 20),

    C("C", 20),

    D("D", 15),

    E("E", 15),

    ;

    private String score;

    private int probability;

    StudentCourseScoreEnum(String score, int probability)
    {
        this.score = score;
        this.probability = probability;
    }

    public String getScore()
    {
        return score;
    }

    public String getValue()
    {
        return score;
    }

    public int getProbability()
    {
        return probability;
    }
}
