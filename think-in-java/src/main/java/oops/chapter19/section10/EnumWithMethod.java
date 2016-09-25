/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter19.section10;

import java.text.DateFormat;
import java.util.Date;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-17
 * Project        : desultory-essay
 * File Name      : EnumWithMethod.java
 */
public enum EnumWithMethod
{
    DATE_TIME(1, "datetime")
            {
                @Override
                String getInfo()
                {
                    return DateFormat.getDateInstance().format(new Date());
                }
            },

    CLASSPATH(2, "classpath")
            {
                @Override
                String getInfo()
                {
                    return System.getenv("CLASSPATH");
                }

                // 重写已有方法
                @Override
                public String getMessage()
                {
                    return super.getMessage() + " modified";
                }
            },

    VERSION(3, "version")
            {
                @Override
                String getInfo()
                {
                    return System.getProperty("java.version");
                }
            };

    private int type;

    private String message;

    abstract String getInfo();

    EnumWithMethod(int type, String message)
    {
        this.type = type;
        this.message = message;
    }

    public int getType()
    {
        return type;
    }

    public String getMessage()
    {
        return message;
    }

    public static void main(String[] args)
    {
        System.out.println(EnumWithMethod.VERSION.getInfo());
        // 1.7.0_79
    }
}
