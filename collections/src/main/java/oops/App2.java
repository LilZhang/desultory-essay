/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-09-19
 * Project        : desultory-essay
 * File Name      : App2.java
 */
public class App2
{
    private static final String PATH = "/home/lilzhang/Desktop/herb3.txt";

    public static void main(String[] args)
    {
        BufferedReader in = null;
        try
        {
            FileReader fin = new FileReader(PATH);
            in = new BufferedReader(fin);

            String xc = in.readLine();
            String qc = in.readLine();
            Set<String> xcSet = new HashSet<String>(Arrays.asList(xc.split(",")));
            Set<String> qcSet = new HashSet<String>(Arrays.asList(qc.split(",")));

            xcSet.retainAll(qcSet);
            System.out.println();

            StringBuilder sb = new StringBuilder();
            for (String s : xcSet)
            {
                sb.append(s).append(",");
            }

            System.out.println(sb.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException ignore)
                {

                }
            }
        }





    }

}

