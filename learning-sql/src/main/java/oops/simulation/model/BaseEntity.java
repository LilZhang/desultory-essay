/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation.model;

import java.io.Serializable;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : BaseEntity.java
 */
public class BaseEntity implements Serializable
{
    private static int counter = 0;

    protected int id = ++counter;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        id = id;
    }
}
