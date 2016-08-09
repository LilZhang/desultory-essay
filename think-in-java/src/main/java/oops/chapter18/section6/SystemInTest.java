/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section6;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-08-07
 * Project        : desultory-essay
 * File Name      : SystemInTest.java
 */
public class SystemInTest
{
    public static void main(String[] args)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(System.in));
            String s;
            while ((s = reader.readLine()) != null && s.length() > 0)
            {
                System.out.println(s);
            }
        }
        catch (Exception e)
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
                catch (Exception e)
                {
                    // ignore
                }
            }
        }
    }
}
