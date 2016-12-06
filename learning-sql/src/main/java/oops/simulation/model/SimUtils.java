/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation.model;

import oops.simulation.annotation.Count;
import oops.simulation.annotation.ValueRange;
import oops.simulation.enums.CountryEnum;
import oops.simulation.enums.Probable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;
import java.lang.Class;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : SimUtils.java
 */
public class SimUtils
{
    private static final String NAME_PATH = "/simulation/names_all.txt";

    private static Map<String, Map<String, List<String>>> nameMap;

    static
    {
        initNameMap();
    }

    public static <T extends Enum<T> & Probable> T random(java.lang.Class<T> enumClass)
    {
        T[] enumConstants = enumClass.getEnumConstants();
        int len = 0;
        for (T t : enumConstants)
        {
            len += t.getProbability();
        }

        int i = (int) (Math.random() * len);

        int base = 0, ceil = 0;
        for (T t : enumConstants)
        {
            ceil += t.getProbability();
            if (i >= base && i < ceil)
            {
                return t;
            }
            base = ceil;
        }
        return null;
    }

    public static String randomName(CountryEnum countryEnum, String gender)
    {
        String result = "";
        if (countryEnum != null && gender != null && gender.length() > 0)
        {
            List<String> resultList = new ArrayList<String>();
            Map<String, List<String>> languageMap = nameMap.get(gender);
            for (String language : countryEnum.getPhylum())
            {
                List<String> c = languageMap.get(language);
                if (c != null && c.size() > 0)
                {
                    resultList.addAll(c);
                }
            }

            Map<String, List<String>> languageMapAll = nameMap.get("all");
            if (languageMapAll != null && languageMapAll.size() > 0)
            {
                for (String language : countryEnum.getPhylum())
                {
                    List<String> c = languageMapAll.get(language);
                    if (c != null && c.size() > 0)
                    {
                        resultList.addAll(c);
                    }
                }
            }
            Collections.shuffle(resultList);
            result = resultList.get(0);
        }
        return result;
    }

    public static String genCreateTableSQL(java.lang.Class<?> clazz)
    {
        String formatTable = "CREATE TABLE test.%s (%s);";
        String formatColumn = "%s %s NOT NULL DEFAULT %s";

        if (clazz == null)
        {
            return "";
        }
        String simpleName = clazz.getSimpleName();
        List<Field> fields = new ArrayList<Field>();
        Field[] supFields = clazz.getSuperclass().getDeclaredFields();
        Field[] fields1 = clazz.getDeclaredFields();
        fields.addAll(Arrays.asList(supFields));
        fields.addAll(Arrays.asList(fields1));

        if (fields.size() > 0)
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fields.size(); i++)
            {
                Field field = fields.get(i);
                Class<?> aclazz = field.getType();
                String fieldName = upperFirstLetter(field.getName());
                String type = "", def = "";
                if (aclazz.equals(int.class))
                {
                    type = "INTEGER";
                    def = "0";
                }
                else if (aclazz.equals(String.class))
                {
                    type = "VARCHAR";
                    def = "\'\'";
                }
                sb.append(String.format(formatColumn, fieldName, type, def));
                if (i != (fields.size() - 1))
                {
                    sb.append(", ");
                }
            }
            return String.format(formatTable, underlineName(simpleName), sb.toString());
        }
        return null;
    }

    public static <T> String genInsertSQL(Class<T> clazz, List<T> list)
    {
        String formatHead = "INSERT INTO test.%s(%s) VALUES ";
        String formatContent = " (%s)";

        if (clazz == null || list == null || list.size() ==0)
        {
            return "";
        }


        String tableName = underlineName(clazz.getSimpleName());
        List<Field> fields = new ArrayList<Field>();
        Field supField = null;
        try
        {
            supField = clazz.getSuperclass().getDeclaredField("id");
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        Field[] fields1 = clazz.getDeclaredFields();
        fields.add(supField);
        fields.addAll(Arrays.asList(fields1));

        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < fields.size(); i++)
        {
            sb1.append(upperFirstLetter(fields.get(i).getName()));
            if (i != (fields.size() - 1))
            {
                sb1.append(", ");
            }
        }

        List<String> contentList = new ArrayList<String>(list.size());
        for (T t : list)
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fields.size(); i++)
            {
                Field field = fields.get(i);
                try
                {
                    Object o = field.get(t);
                    sb.append('\'').append(String.valueOf(o)).append('\'');
                    if (i != (fields.size() - 1))
                    {
                        sb.append(", ");
                    }
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
            contentList.add(String.format(formatContent, sb.toString()));
        }


        StringBuilder sb = new StringBuilder();
        sb.append(String.format(formatHead, tableName, sb1.toString()));
        for (int i = 0; i < contentList.size(); i++)
        {
            sb.append(contentList.get(i));
            if (i != (contentList.size() - 1))
            {
                sb.append(", ");
            }
            else
            {
                sb.append(";");
            }
        }
        return sb.toString();
    }

    public static <T extends BaseEntity> List<T> randomObjectList(java.lang.Class<T> clazz)
    {
        List<T> result = Collections.emptyList();

        try
        {
            Count count = clazz.getAnnotation(Count.class);
            if (count != null)
            {
                int cnt = count.value();
                if (cnt > 0)
                {
                    result = new ArrayList<T>(cnt);
                    Set<T> avoidConflict = new HashSet<T>(cnt);
                    Map<Field, java.lang.Class<? extends Enum<?>>> fieldEnumMap =
                            new HashMap<Field, java.lang.Class<? extends Enum<?>>>();
                    for (Field field : clazz.getDeclaredFields())
                    {
                        ValueRange valueRange = field.getAnnotation(ValueRange.class);
                        if (valueRange != null)
                        {
                            java.lang.Class<? extends Enum<?>> enumClass = valueRange.value();
                            fieldEnumMap.put(field, enumClass);
                        }
                    }

                    for (int i = 0; i < cnt; i++)
                    {
                        T t = clazz.newInstance();
                        for (Map.Entry<Field, java.lang.Class<? extends Enum<?>>> entry : fieldEnumMap.entrySet())
                        {
                            Field field = entry.getKey();
                            java.lang.Class enumClass = entry.getValue();
                            Object value = ((Probable) random(enumClass)).getValue();
                            field.set(t, value);
                        }
                        if (avoidConflict.add(t))
                        {
                            result.add(t);
                        }
                        else
                        {
                            i--;
                        }
                    }
                }
            }
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }


        return result;
    }

    private static void initNameMap()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(SimUtils.class.getResourceAsStream(NAME_PATH)));

        try
        {
            nameMap = new HashMap<String, Map<String, List<String>>>();
            String content;
            while ((content = reader.readLine()) != null && content.length() > 0)
            {
                content = content.replace(";", "");
                String[] split = content.split(",");
                String language = split[0];
                String name = split[1];
                String gender = split[2];

                Map<String, List<String>> genderMap = nameMap.get(gender);
                if (genderMap == null)
                {
                    genderMap = new HashMap<String, List<String>>();
                    nameMap.put(gender, genderMap);
                }
                List<String> names = genderMap.get(language);
                if (names == null)
                {
                    names = new ArrayList<String>();
                    genderMap.put(language, names);
                }
                names.add(name);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }
    }

    private static String upperFirstLetter(String string)
    {
        if (string != null && string.length() > 0)
        {
            char c = string.charAt(0);
            if (Character.isLowerCase(c))
            {
                StringBuilder sb = new StringBuilder(string);
                sb.setCharAt(0, Character.toUpperCase(c));
                return sb.toString();
            }
        }
        return string;
    }

    private static String underlineName(String string)
    {
        if (string != null && string.length() > 0)
        {
            char[] chars = string.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chars.length; i++)
            {
                char c = chars[i];
                if (Character.isUpperCase(c))
                {
                    if (i != 0)
                    {
                        sb.append('_');
                    }
                    sb.append(Character.toLowerCase(c));
                }
                else
                {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
        return string;
    }
}
