/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section12;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-15
 * Project        : desultory-essay
 * File Name      : StaticSerialStore.java
 */
public class StaticSerialStore
{
//    Circle	{ color=[3],	xPos=58,	yPos=55,	dimension=93}
//    Square	{ color=[3],	xPos=61,	yPos=61,	dimension=29}
//    Line	    { color=[3],	xPos=68,	yPos=0,	dimension=22}
//    Circle	{ color=[3],	xPos=7,	yPos=88,	dimension=28}
//    Square	{ color=[3],	xPos=51,	yPos=89,	dimension=9}
//    Line	    { color=[3],	xPos=78,	yPos=98,	dimension=61}
//    Circle	{ color=[3],	xPos=20,	yPos=58,	dimension=16}
//    Square	{ color=[3],	xPos=40,	yPos=11,	dimension=22}
//    Line	    { color=[3],	xPos=4,	yPos=83,	dimension=6}
//    Circle	{ color=[3],	xPos=75,	yPos=10,	dimension=42}

    public static void main(String[] args)
    {
        try
        {
            StaticSerial.store();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
