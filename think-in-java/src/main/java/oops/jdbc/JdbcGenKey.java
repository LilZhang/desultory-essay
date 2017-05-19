/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-09
 * Project        : desultory-essay
 * File Name      : JdbcApp.java
 */
public class JdbcGenKey
{
    public static void main(String[] args)
    {
        Properties properties = new Properties();
        FileInputStream in = null;
        try
        {
            in = new FileInputStream("/home/lilzhang/Desktop/db.properties");
            properties.load(in);
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
                catch (IOException e)
                {
                    // ignore
                }
            }
        }

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try
        {
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
            ps = connection.prepareStatement("INSERT INTO user_demo (name, age, birthday) VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            UserDemo userDemo = new UserDemo("elsu hamsick", 31, sdf.parse("1984-07-29"));

            ps.setString(1, userDemo.getName());
            ps.setInt(2, userDemo.getAge());
            ps.setDate(3, new java.sql.Date(userDemo.getBirthday().getTime()));

            int affectNum = ps.executeUpdate();
            resultSet = ps.getGeneratedKeys();
            long key = 0;
            if (resultSet.next())
            {
                key = resultSet.getLong(1);
            }
            System.out.format("affectNum: %d, key: %d", affectNum, key);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (resultSet != null)
            {
                try
                {
                    resultSet.close();
                }
                catch (SQLException e)
                {
                    // ignore
                }
            }

            if (ps != null)
            {
                try
                {
                    ps.close();
                }
                catch (SQLException e)
                {
                    // ignore
                }
            }

            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    // ignore
                }
            }
        }
    }
}
