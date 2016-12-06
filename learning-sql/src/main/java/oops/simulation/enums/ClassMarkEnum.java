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
 * File Name      : ClassMarkEnum.java
 */
public enum ClassMarkEnum implements Probable<String>
{
    A("A", 20),

    B("B", 20),

    C("C", 20),

    D("D", 20),

    E("E", 20),

    ;

    private String grade;

    private int probability;

    ClassMarkEnum(String grade, int probability)
    {
        this.grade = grade;
        this.probability = probability;
    }

    public String getGrade()
    {
        return grade;
    }

    public String getValue()
    {
        return grade;
    }

    public int getProbability()
    {
        return probability;
    }
}
