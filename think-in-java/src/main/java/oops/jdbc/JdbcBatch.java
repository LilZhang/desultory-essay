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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-09
 * Project        : desultory-essay
 * File Name      : JdbcBatch.java
 */
public class JdbcBatch
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
            connection.setAutoCommit(false);    // 重要：关闭自动提交

            ps = connection.prepareStatement("INSERT INTO user_demo (name, age, birthday) VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            for (int i = 0; i < 4; i++)
            {
                UserDemo userDemo = new UserDemo("batch", 31, sdf.parse("1984-07-29"));
                ps.setString(1, userDemo.getName());
                ps.setInt(2, userDemo.getAge());
                ps.setDate(3, new java.sql.Date(userDemo.getBirthday().getTime()));
                ps.addBatch();  // 批量
            }

            int[] affectNums = ps.executeBatch();
            connection.commit();

            resultSet = ps.getGeneratedKeys();

            List<Long> keys = new ArrayList<Long>();
            while (resultSet.next())
            {
                keys.add(resultSet.getLong(1));
            }
            System.out.format("affectNums: %s, key: %s", Arrays.toString(affectNums), Arrays.toString(keys.toArray()));
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
