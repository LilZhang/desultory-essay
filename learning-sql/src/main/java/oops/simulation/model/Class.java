/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation.model;

import oops.simulation.annotation.ValueRange;
import oops.simulation.enums.ClassEliteEnum;
import oops.simulation.enums.ClassMarkEnum;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : Class.java
 */
public class Class extends BaseEntity
{
    @ValueRange(ClassMarkEnum.class)
    protected String mark;

    @ValueRange(ClassEliteEnum.class)
    protected int isElite;

    protected int majorId;

    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public int getIsElite()
    {
        return isElite;
    }

    public void setIsElite(int isElite)
    {
        this.isElite = isElite;
    }

    public int getMajorId()
    {
        return majorId;
    }

    public void setMajorId(int majorId)
    {
        this.majorId = majorId;
    }
}
