/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-13
 * Project        : desultory-essay
 * File Name      : App3.java
 */
public class App3
{
    private static final String KEYS = "69, 35, 32, 2, 71, 36, 6, 37, 9, 11, 13, 45, 15, 85, 84, 87, 86, 18, 21, 83, 59, 92, 56, 94, 91";

    public static void main(String[] args)
    {
        Object object = new Object();
        TreeMap<Integer, Object> treeMap = new TreeMap<Integer, Object>();

        for (String s : KEYS.split(", "))
        {
            treeMap.put(Integer.parseInt(s), object);
        }


        treeMap.remove(15);
        treeMap.remove(18);
        treeMap.remove(59);
        treeMap.remove(2);
        treeMap.remove(32);
        treeMap.remove(36);
        treeMap.remove(37);
        treeMap.remove(91);
        treeMap.remove(71);
        treeMap.remove(6);
        treeMap.remove(56);
        treeMap.remove(92);
        treeMap.remove(87);
        treeMap.remove(21);
        treeMap.remove(86);
        treeMap.remove(45);
        treeMap.remove(9);
        treeMap.remove(94);
        treeMap.remove(11);
        treeMap.remove(84);

        System.out.println(treeMap.getRoot());

//        List<Integer> list = new ArrayList<Integer>();
//        for (String s : KEYS.split(", "))
//        {
//            int i = Integer.parseInt(s);
//            list.add(i);
//        }
//        Collections.shuffle(list);
//
//        StringBuilder sb = new StringBuilder();
//        for (Integer integer : list)
//        {
//            sb.append(integer).append(", ");
//        }
//
//        System.out.println(sb.toString());
    }
}
