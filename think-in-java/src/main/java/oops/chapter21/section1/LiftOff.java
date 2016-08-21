/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter21.section1;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-20
 * Project        : desultory-essay
 * File Name      : LiftOff.java
 */
public class LiftOff implements Runnable
{
    private int id = ++counter;

    private static int counter = 0;

    private static Random random = new Random();

    private static DecimalFormat df = new DecimalFormat("0.000");

    public void run()
    {
        System.out.println("#" + id + " start.");

        for (int i = 9; i >= 0 ; i--)
        {
            try
            {
                long start = System.nanoTime();
                Thread.sleep(getInt());
                String format = df.format((System.nanoTime() - start) / 1000000000d);
                System.out.println("#" + id + " [ " + i + " ]. time: " + format);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("#" + id + " over.");
    }

    private static int getInt()
    {
        return random.nextInt(6000) + 1000;
    }

}
