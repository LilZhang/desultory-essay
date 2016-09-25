/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-17
 * Project        : desultory-essay
 * File Name      : App7.java
 */
public class App7
{
    public static void main(String[] args)
    {
        Object object = new Object();
        Object[] array = new Object[10];
        Arrays.fill(array, object);

        List<Object> list = Collections.nCopies(10, object);
        System.out.println();

    }
}
