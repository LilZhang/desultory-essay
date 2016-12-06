/*
 *
 * Copyright (c) 2111-2115 by Shanghai HanTao Information Co., Ltd.
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
 * File Name      : CourseNameEnum.java
 */
public enum CourseNameEnum implements Probable<String>
{
    HISTORY_OF_CCP("history of ccp", 1),

    SPECIAL_EDUCATION("special education", 1),

    SANSKRIT_PALI_LANGUAGES("sanskrit pali languages", 1),

    NUCLEAR_PHYSICS("nuclear physics", 1),

    THAI_LANGUAGE("thai language", 1),

    URBAN_RURAL_PLANNING("urban rural planning", 1),

    GEOMATICS_ENGINEERING("geomatics engineering", 1),
    
    FLIGHT_VEHICLE_DESIGN("flight vehicle design", 1),

    EXPLOSION_TECHNOLOGY("explosion technology", 1),

    VETERINARY_MEDICINE("veterinary medicine", 1),

    MEDICAL_JURISPRUDENCE("medical jurisprudence", 1),

    CRIMINAL_INVESTIGATION("criminal investigation", 1),

    ;

    private String name;

    private int probability;

    CourseNameEnum(String name, int probability)
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
