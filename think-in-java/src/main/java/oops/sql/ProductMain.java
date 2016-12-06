/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.sql;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import oops.sql.domain.ProductCategoryId;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-11
 * Project        : desultory-essay
 * File Name      : ProductMain.java
 */
public class ProductMain
{
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    private static final String CONFIG = "/home/lilzhang/Desktop/product_acc.properties";

    public static void main(String[] args)
    {
        FileInputStream in = null;
        Session session = null;
        Connection connection = null;

        try
        {
            in = new FileInputStream(CONFIG);
            Properties properties = new Properties();
            properties.load(in);

            String host = properties.getProperty("host");
            int port = Integer.parseInt(properties.getProperty("port"));
            String user = properties.getProperty("user");
            String pass = properties.getProperty("pass");
            String proxyHost = properties.getProperty("proxy_host");
            int proxyPort = Integer.parseInt(properties.getProperty("proxy_port"));
            String proxyUser = properties.getProperty("proxy_user");
            String proxyPass = properties.getProperty("proxy_pass");
            String dbName = properties.getProperty("db_name");

            JSch jSch = new JSch();
            session = jSch.getSession(proxyUser, proxyHost, proxyPort);
            session.setPassword(proxyPass);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            System.out.println(session.getServerVersion());
            int assignedLocalPort = session.setPortForwardingL(port, host, port);
            System.out.println("localhost:" + assignedLocalPort + " -> " + host + ":" + port);

            Class.forName(MYSQL_DRIVER);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, user, pass);
//            List<ProductCategoryId> query = query(connection, ProductCategoryId.class, "SELECT pc.Id FROM product_category pc WHERE pc.ParentId = ?;", new Integer(8));

            List<Integer> leafIds = getLeafIds(connection, 33657);

            StringBuilder sb = new StringBuilder();
            for (Integer leafId : leafIds)
            {
                sb.append(leafId).append(",");
            }
            System.out.println(sb.toString());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSchException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
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

            if (session != null)
            {
                session.disconnect();
            }

            if (connection != null)
            {
                try
                {
                    connection.close();
                }
                catch (SQLException ignore)
                {

                }
            }
        }
    }

    private static List<Integer> getLeafIds(Connection connection, Integer id)
    {
        List<Integer> result = new ArrayList<Integer>();

        List<ProductCategoryId> productCategoryIds = query(connection, ProductCategoryId.class, "SELECT pc.Id FROM product_category pc WHERE pc.ParentId = ?;", id);

        if (productCategoryIds.size() > 0)
        {
            for (ProductCategoryId categoryId : productCategoryIds)
            {
                result.addAll(getLeafIds(connection, categoryId.getId()));
            }
        }
        else
        {
            result.add(id);
        }

        return result;
    }

    private static <T> List<T> query(Connection connection, Class<T> clazz, String sqlTemp, Object... params)
    {
        List<T> result = Collections.emptyList();
        FieldType[] fieldTypes = null;
        String[] fieldNames = null;

        if (connection != null)
        {
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try
            {
                preparedStatement = connection.prepareStatement(sqlTemp);
                for (int i = 0; i < params.length; i++)
                {
                    Object param = params[i];
                    int indexInSQL = i + 1;

                    if (param instanceof Integer)
                    {
                        preparedStatement.setInt(indexInSQL, ((Integer) param));
                    }
                    else if (param instanceof String)
                    {
                        preparedStatement.setString(indexInSQL, ((String) param));
                    }
                    else
                    {
                        throw new IllegalArgumentException("type not supported yet");
                    }
                }

                resultSet = preparedStatement.executeQuery();
                while (resultSet.next())
                {
                    if (result == Collections.emptyList())
                    {
                        result = new ArrayList<T>();
                    }
                    if (clazz == null)
                    {
                        throw new IllegalArgumentException("class cannot be null");
                    }

                    if (fieldTypes == null || fieldNames == null)
                    {
                        Field[] declaredFields = clazz.getDeclaredFields();
                        fieldTypes = new FieldType[declaredFields.length];
                        fieldNames = new String[declaredFields.length];
                        for (int i = 0; i < declaredFields.length; i++)
                        {
                            Type annotation = declaredFields[i].getAnnotation(Type.class);
                            if (annotation != null)
                            {
                                fieldTypes[i] = annotation.value();
                                fieldNames[i] = declaredFields[i].getName();
                            }
                        }
                    }

                    T t = clazz.newInstance();
                    for (int i = 0; i < fieldTypes.length; i++)
                    {
                        if (fieldTypes[i] != null)
                        {
                            Class<?> aClass = null;
                            Object object = null;

                            switch (fieldTypes[i])
                            {
                                case INT:
                                    aClass = int.class;
                                    Integer anInt = resultSet.getInt(i + 1);
                                    object = anInt;
                                    break;
                                case VARCHAR:
                                    aClass = String.class;
                                    String string = resultSet.getString(i + 1);
                                    object = string;
                                    break;
                                case TIMESTAMP:
                                    aClass = java.util.Date.class;
                                    Timestamp timestamp = resultSet.getTimestamp(i + 1);
                                    object = timestamp;
                                    break;
                                default:
                                    throw new IllegalArgumentException("type not supported yet");
                            }

                            Method method = clazz.getMethod(genSetterName(fieldNames[i]), aClass);
                            method.invoke(t, object);
                        }
                    }
                    result.add(t);
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (NoSuchMethodException e)
            {
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (preparedStatement != null)
                {
                    try
                    {
                        preparedStatement.close();
                    }
                    catch (SQLException ignore)
                    {

                    }
                }

                if (resultSet != null)
                {
                    try
                    {
                        resultSet.close();
                    }
                    catch (SQLException ignore)
                    {

                    }
                }
            }
        }

        return result;
    }

    private static String genSetterName(String fieldName)
    {
        String result = "";

        if (fieldName != null && fieldName.length() > 0)
        {
            char[] chars = fieldName.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            StringBuilder sb = new StringBuilder();
            sb.append("set");
            for (char c : chars)
            {
                sb.append(c);
            }
            result = sb.toString();
        }

        return result;
    }
}
