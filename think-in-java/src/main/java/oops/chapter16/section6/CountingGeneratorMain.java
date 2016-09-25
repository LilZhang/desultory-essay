/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter16.section6;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-07-25
 * Project        : desultory-essay
 * File Name      : CountingGeneratorMain.java
 */
public class CountingGeneratorMain
{
    public static void main(String[] args)
    {
        int size = 10;

        Class<?>[] classes = CountingGenerator.class.getClasses();

        for (Class<?> clazz : classes)
        {
            try
            {
                CountingGenerator.Generator<?> generator = (CountingGenerator.Generator<?>) clazz.newInstance();

                for (int i = 0; i < size; i++)
                {
                    System.out.print(generator.next() + " ");
                }
                System.out.println();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
