/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.calculator.propertydescriptor;

import java.beans.PropertyDescriptor;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-23
 * Project        : desultory-essay
 * File Name      : PropertyDescriptorMain.java
 */
public class PropertyDescriptorMain
{
    public static void main(String[] args)
    {
        try
        {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", Person.class);
//            propertyDescriptor.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
