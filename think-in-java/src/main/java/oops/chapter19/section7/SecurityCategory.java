/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter19.section7;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-17
 * Project        : desultory-essay
 * File Name      : SecurityCategory.java
 */
public enum SecurityCategory
{
    STOCK(1, Security.Stock.class),

    BOND(2, Security.Bond.class);

    private int type;

    private Security[] values;

    SecurityCategory(int type, Class<? extends Security> ec)
    {
        this.type = type;
        this.values = ec.getEnumConstants();
    }

    interface Security
    {
        int getType();

        String getMessage();

        enum Stock implements Security
        {
            SHORT(1, "short"),

            LONG(2, "long"),

            MARGIN(3, "margin");

            private int type;

            private String message;

            Stock(int type, String message)
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
        }

        enum Bond implements Security
        {
            MUNICIPAL(1, "municipal"),

            JUNK(2, "junk");

            private int type;

            private String message;

            Bond(int type, String message)
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
        }
    }
}
