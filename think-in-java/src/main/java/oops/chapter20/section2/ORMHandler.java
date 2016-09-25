/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter20.section2;

import oops.App;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-17
 * Project        : desultory-essay
 * File Name      : ORMHandler.java
 */
public class ORMHandler
{
    public static void main(String[] args)
    {
        try
        {
            Class<?> clazz = App.class;
            ORM.DBTable dbTable = clazz.getAnnotation(ORM.DBTable.class);
            if (dbTable != null)
            {
                String tableName = dbTable.name();
                if (tableName.length() < 0)
                {
                    tableName = clazz.getName().toLowerCase();
                }

                List<String> columnDefs = new ArrayList<String>();
                for (Field field : clazz.getDeclaredFields())
                {
                    String columnName = null;
                    Annotation[] fieldAnnos = field.getDeclaredAnnotations();
                    if (fieldAnnos != null && fieldAnnos.length > 0)
                    {
                        for (Annotation anno : fieldAnnos)
                        {
                            String name = null;

                            if (anno instanceof ORM.SQLInteger)
                            {
                                ORM.SQLInteger sqlInteger = (ORM.SQLInteger) anno;
                                name = sqlInteger.name();

                            }
                            else if (anno instanceof ORM.SQLString)
                            {
                                ORM.SQLString sqlString = (ORM.SQLString) anno;
                                name = sqlString.name();

                            }

                            if (name.length() > 0)
                            {
                                columnName = name;
                            }
                        }
                    }

                    if (columnName != null)
                    {
                        columnDefs.add(columnName);
                    }
                }
            }

            System.out.println();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
