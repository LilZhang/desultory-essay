/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-09-19
 * Project        : desultory-essay
 * File Name      : App11.java
 */
public class App11
{
    public static void main(String[] args)
    {
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new InputStreamReader(System.in));
            String str = null;
            while ((str = reader.readLine()) != null && !"exit".equals(str))
            {
                System.out.println(str);
            }
        }
        catch (IOException e)
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
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
