/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section1;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-03
 * Project        : desultory-essay
 * File Name      : DirFilter.java
 */
public class DirFilter implements FilenameFilter
{
    public boolean accept(File dir, String name)
    {
        boolean result = false;

        if (name != null && name.startsWith("sv"))
        {
            result = true;
        }
        return result;
    }

    public static void main(String[] args)
    {
        File file = new File("E:\\DESKTOP\\testDir");
        String[] list = file.list(new DirFilter());
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String s : list)
        {
            System.out.println(s);
            // sv1.txt
            // sv2.txt
            // sv3.txt
            // sv4.txt
        }

        // or
        final String startWith = "dv";

        String[] list1 = file.list(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                boolean result = false;
                if (name != null && name.startsWith(startWith))
                {
                    result = true;
                }
                return result;
            }
        });

        for (String s : list1)
        {
            System.out.println(s);
            // dv1.txt
            // dv2.txt
            // dv3.txt
            // dv4.txt
        }
    }
}
