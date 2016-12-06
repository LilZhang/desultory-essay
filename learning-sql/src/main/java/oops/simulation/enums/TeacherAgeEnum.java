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
 * File Name      : StudentAgeEnum.java
 */
public enum TeacherAgeEnum implements Probable<Integer>
{
    X30(30, 3),

    X35(35, 10),

    X40(40, 20),

    X45(45, 25),

    X50(50, 25),

    X55(55, 20),

    X60(60, 10),

    X65(65, 8),

    X70(70, 5),

    X75(75, 3),

    X80(80, 2),

    X85(85, 1),

    X90(90, 1),

    X95(95, 1),


    ;

    private int age;

    private int probability;

    TeacherAgeEnum(int age, int probability)
    {
        this.age = age;
        this.probability = probability;
    }

    public int getAge()
    {
        return age;
    }

    public Integer getValue()
    {
        return age;
    }

    public int getProbability()
    {
        return probability;
    }
}
