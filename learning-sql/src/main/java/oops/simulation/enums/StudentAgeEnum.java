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
public enum StudentAgeEnum implements Probable<Integer>
{


    X17(17, 5),

    X18(18, 5),

    X19(19, 20),

    X20(20, 20),

    X21(21, 20),

    X22(22, 20),

    X23(23, 5),

    X24(24, 5),

    ;

    static
    {

    }

    private int age;

    private int probability;

    StudentAgeEnum(int age, int probability)
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
