/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section9;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-07
 * Project        : desultory-essay
 * File Name      : ProcessStreamTest.java
 */
public class ProcessStreamTest
{
    public static void main(String[] args)
    {
        try
        {
            Process process = new ProcessBuilder("ipconfig").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null)
            {
                System.out.println(s);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
