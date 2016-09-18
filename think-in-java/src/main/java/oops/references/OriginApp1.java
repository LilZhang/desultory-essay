/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-16
 * Project        : desultory-essay
 * File Name      : App1.java
 */
public class OriginApp1
{
    private static final Random random = new Random();

    private static final char[] chars = ("abcdefghijklmnopqrstuvwxyz"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    public static void main(String[] args)
    {
        ArrayObj[] arrayObjs = new ArrayObj[10];
        ArrayObj arrayObj = new ArrayObj("GFJFDS");
        Arrays.fill(arrayObjs, arrayObj);

        List<ArrayObj> objList = new ArrayList<ArrayObj>(10);
        objList.add(null);
//        Collections.fill(objList, arrayObj);
        List<ArrayObj> objList1 = Collections.nCopies(10, arrayObj);

        int length = 10;
        String[] strArray = new String[length];
        String[] strArray2 = new String[length];

        for (int i = 0; i < length; i++)
        {
            strArray[i] = genRandomStr(10);
            strArray2[i] = genRandomStr(10);
        }
        System.out.println(Arrays.toString(strArray));
        System.out.println(Arrays.toString(strArray2));
        System.out.println(Arrays.equals(strArray, strArray2));


        Arrays.sort(strArray, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(strArray));

        List<String> strList = new ArrayList<String>(Arrays.asList(strArray2));
        Collections.sort(strList, String.CASE_INSENSITIVE_ORDER);

        System.out.println();
    }

    public static <T> void genArrays(Class<T> clazz)
    {
        T[] t = ((T[]) Array.newInstance(clazz, 10));
        T[][][] t2 = ((T[][][]) Array.newInstance(clazz, 10, 10, 10));
    }

    public static String genRandomStr(int length)
    {
        char[] chars1 = new char[length];

        for (int i = 0; i < length; i++)
        {
            chars1[i] = chars[random.nextInt(chars.length)];
        }

        return new String(chars1);
    }
}

class ArrayObj
{
    private final String id;

    public ArrayObj(String id)
    {
        this.id = id;
    }
}
