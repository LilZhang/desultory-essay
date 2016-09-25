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
 * File Name      : StaticSerialRecover.java
 */
public class StaticSerialRecover
{
//    Circle	{ color=[1],	xPos=58,	yPos=55,	dimension=93}
//    Square	{ color=[0],	xPos=61,	yPos=61,	dimension=29}
//    Line	    { color=[3],	xPos=68,	yPos=0,	dimension=22}
//    Circle	{ color=[1],	xPos=7,	yPos=88,	dimension=28}
//    Square	{ color=[0],	xPos=51,	yPos=89,	dimension=9}
//    Line	    { color=[3],	xPos=78,	yPos=98,	dimension=61}
//    Circle	{ color=[1],	xPos=20,	yPos=58,	dimension=16}
//    Square	{ color=[0],	xPos=40,	yPos=11,	dimension=22}
//    Line	    { color=[3],	xPos=4,	yPos=83,	dimension=6}
//    Circle	{ color=[1],	xPos=75,	yPos=10,	dimension=42}

    //小结：
    // 1.Circle 的静态字段 color 在字段初始化时初始化，所以反序列化的时候为 1
    // 2.Square 的静态字段 color 在构造器内初始化。Serializable对象完全以储存的二进制为基础构造，不调用构造器。
    // 3.Line 的静态字段 color 在字段初始化时初始化。但有了针对 color 的序列化与反序列化方法，值得以被反序列化恢复
    public static void main(String[] args)
    {
        try
        {
            StaticSerial.recover();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
