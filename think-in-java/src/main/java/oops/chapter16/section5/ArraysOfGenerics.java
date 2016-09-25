/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter16.section5;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-25
 * Project        : desultory-essay
 * File Name      : ArraysOfGenerics.java
 */
public class ArraysOfGenerics
{
    public static void main(String[] args)
    {
        System.out.println();

        List<String>[] ls;
        List[] la = new List[10];
        ls = ((List<String>[]) la);
        ls[0] = new ArrayList<String>();
        // ls[1] = new ArrayList<Integer>();    // error

        Object[] objects = ls;
        objects[1] = new ArrayList<Integer>();

        List<SomeHow>[] lsh = ((List<SomeHow>[]) new List[10]);
        for (int i = 0; i < lsh.length; i++)
        {
            lsh[i] = new ArrayList<SomeHow>();
        }
    }
}

class SomeHow
{

}
