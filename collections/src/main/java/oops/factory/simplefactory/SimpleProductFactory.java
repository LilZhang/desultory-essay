/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.factory.simplefactory;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-12-28
 * Project        : desultory-essay
 * File Name      : SimpleProductFactory.java
 */

// 简单工厂模式
public class SimpleProductFactory
{
    public Product createProduct(int type)
    {
        switch (type)
        {
            case 1:
                return new ProductA();
            case 2:
                return new ProductB();
            default:
                return null;
        }
    }
}
