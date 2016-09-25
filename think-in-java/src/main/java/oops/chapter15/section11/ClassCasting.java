/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter15.section11;

import oops.chapter15.section10.Holder;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-19
 * Project        : desultory-essay
 * File Name      : ClassCasting.java
 */
public class ClassCasting
{
    public static void main(String[] args) throws Exception
    {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[0]));
        // error
        // List<Holder> hl1 = List<Holder>.class.cast(in.readObject());
        List<Holder> hl2 = List.class.cast(in.readObject());    // ok
    }
}
