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
 * File Name      : ClassEliteEnum.java
 */
public enum ClassEliteEnum implements Probable<Integer>
{
    YES(1, 20),

    NO(0, 80),

    ;

    private int isElite;

    private int probability;

    ClassEliteEnum(int isElite, int probability)
    {
        this.isElite = isElite;
        this.probability = probability;
    }

    public int getIsElite()
    {
        return isElite;
    }

    public Integer getValue()
    {
        return isElite;
    }

    public int getProbability()
    {
        return probability;
    }
}
