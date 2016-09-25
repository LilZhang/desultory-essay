/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-08-20
 * Project        : desultory-essay
 * File Name      : App2.java
 */
public class App2
{
    public static void main(String[] args)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(
                    new InputStreamReader(System.in));
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
