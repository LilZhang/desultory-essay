/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.section2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-25
 * Project        : desultory-essay
 * File Name      : FillingLists.java
 */
public class FillingLists
{
    public static void main(String[] args)
    {
        List<StringAddress> list = new ArrayList<StringAddress>(
                Collections.nCopies(4, new StringAddress("hello")));
        System.out.println(list);

        Collections.fill(list, new StringAddress("world"));
        System.out.println(list);
    }
}

class StringAddress
{
    private String s;

    public StringAddress(String s)
    {
        this.s = s;
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + s;
    }
}
