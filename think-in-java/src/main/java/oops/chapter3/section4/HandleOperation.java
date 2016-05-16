/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter3.section4;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-05-16
 * Project        : desultory-essay
 * File Name      : handleOperation.java
 */
public class HandleOperation
{
    public static void main(String[] args)
    {
        Tank tank1 = new Tank();
        Tank tank2 = new Tank();
        tank1.level = 11;
        tank2.level = 25;
        System.out.println(tank1.level + " / " + tank2.level);  // 11 / 25

        tank1 = tank2;
        System.out.println(tank1.level + " / " + tank2.level);  // 25 / 25

        tank2.level = 66;
        System.out.println(tank1.level + " / " + tank2.level);  // 66 / 66
    }

    public static class Tank
    {
        int level;
    }
}
