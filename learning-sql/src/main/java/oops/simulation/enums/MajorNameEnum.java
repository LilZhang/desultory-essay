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
 * File Name      : MajorNameEnum.java
 */
public enum MajorNameEnum implements Probable<String>
{
    PHILOSOPHY("philosophy", 0),

    ECONOMICS("economics", 0),

    LAW("law", 0),

    POLITICAL("political", 0),

    EDUCATION("education", 0),

    LITERATURE("literature", 0),

    LANGUAGE("language", 0),

    HISTORY("history", 0),

    MATHEMATICS("mathematics", 0),

    PHYSICS("physics", 0),

    CHEMISTRY("chemistry", 0),

    GEOGRAPHY("geography", 0),

    BIOLOGICAL("biological", 0),

    MECHATRONICS("mechatronics", 0),

    MATERIALS("materials", 0),

    ELECTRONIC("electronic", 0),

    COMPUTER("computer", 0),

    AUTOMATION("automation", 0),

    TRANSPORTATION("transportation", 0),

    WEAPONRY("weaponry", 0),

    AERONAUTICS("aeronautics", 0),

    NUCLEAR("nuclear", 0),

    ARCHITECTURE("architecture", 0),

    MEDICINE("medicine", 0),

    PHARMACY("pharmacy", 0),

    MANAGEMENT("management", 0),

    ARTS("arts", 0),

    ;

    private String name;

    private int probability;

    MajorNameEnum(String name, int probability)
    {
        this.name = name;
        this.probability = probability;
    }

    public String getName()
    {
        return name;
    }

    public String getValue()
    {
        return name;
    }

    public int getProbability()
    {
        return probability;
    }
}
