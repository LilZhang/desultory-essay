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
 * File Name      : StudentGradeEnum.java
 */
public enum StudentGradeEnum implements Probable<Integer>
{
    G2011(2011, 5),

    G2012(2012, 8),

    G2013(2013, 21),

    G2014(2014, 21),

    G2015(2015, 23),

    G2016(2016, 22),

    ;

    private int grade;

    private int probability;

    StudentGradeEnum(int grade, int probability)
    {
        this.grade = grade;
        this.probability = probability;
    }

    public int getGrade()
    {
        return grade;
    }

    public Integer getValue()
    {
        return grade;
    }

    public int getProbability()
    {
        return probability;
    }
}
