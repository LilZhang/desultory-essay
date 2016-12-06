/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sql.domain;

import oops.sql.FieldType;
import oops.sql.Type;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-11
 * Project        : desultory-essay
 * File Name      : ProductCategoryId.java
 */
public class ProductCategoryId
{
    @Type(FieldType.INT)
    private int id;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
