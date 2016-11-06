/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-03
 * Project        : desultory-essay
 * File Name      : App37.java
 */
public class App37
{
    private static final String PROPERTIES_PATH = "/home/lilzhang/Desktop/account.properties";

    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    private static final String[] COLUMNS = "Id,UserName,Password,Type,Status,Mail,Mobile,Depart,Info,AddTime,UpdateTime,CnName,EnName".split(",");

    private static final Type[] COLUMN_PROP = new Type[]
            {
                    Type.INT, Type.VARCHAR, Type.VARCHAR, Type.INT, Type.INT, Type.VARCHAR, Type.VARCHAR, Type.VARCHAR, Type.VARCHAR,
                    Type.TIMESTAMP, Type.TIMESTAMP, Type.VARCHAR, Type.VARCHAR
            };

    private static String CONNECTION_URL;

    private static String CONNECTION_USERNAME;

    private static String CONNECTION_PASSWORD;

    static
    {
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(PROPERTIES_PATH);
            Properties properties = new Properties();
            properties.load(in);
            CONNECTION_URL = properties.getProperty("url", "");
            CONNECTION_USERNAME = properties.getProperty("username", "");
            CONNECTION_PASSWORD = properties.getProperty("password", "");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException ignore)
                {

                }
            }
        }
    }

    public static void main(String[] args)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            Class<?> clazz = Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection(CONNECTION_URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM admin_user WHERE id = ?");   // 防止 SQL 注入攻击
            preparedStatement.setInt(1, 23);

            resultSet = preparedStatement.executeQuery();
            StringBuilder sb = new StringBuilder();
            while (resultSet.next())
            {
                for (int i = 0; i < COLUMNS.length; i++)
                {
                    Type type = COLUMN_PROP[i];

                    sb.append(COLUMNS[i]);
                    sb.append(": ");

                    switch (type)
                    {
                        case INT:
                            sb.append(resultSet.getInt(i + 1)); // 第一个竟然是 1 不是 0
                            sb.append("\n");
                            break;
                        case VARCHAR:
                            sb.append(resultSet.getString(i + 1));
                            sb.append("\n");
                            break;
                        case TIMESTAMP:
                            sb.append(resultSet.getTimestamp(i + 1));
                            sb.append("\n");
                            break;
                    }
                }
            }

            System.out.println(sb.toString());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }

                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }

                if (connection != null)
                {
                    connection.close();
                }
            }
            catch (SQLException ignore)
            {

            }
        }
    }

    enum Type
    {
        INT, VARCHAR, TIMESTAMP;
    }
}
