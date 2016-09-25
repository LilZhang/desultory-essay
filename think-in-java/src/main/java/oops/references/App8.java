/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-17
 * Project        : desultory-essay
 * File Name      : App8.java
 */
public class App8
{

    public static void main(String[] args)
    {
        File file = new File("E:\\DESKTOP");

        String[] list = file.list(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                if (name.endsWith(".lnk"))
                {
                    return true;
                }
                return false;
            }
        });
        System.out.println(Arrays.toString(list));
    }


}
