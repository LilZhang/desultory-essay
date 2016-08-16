/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter19.section1;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-16
 * Project        : desultory-essay
 * File Name      : Burrito.java
 */
public class Burrito
{
    Spiciness degree;

    public Burrito(Spiciness degree)
    {
        this.degree = degree;
    }

    public Spiciness getDegree()
    {
        return degree;
    }

    public void setDegree(Spiciness degree)
    {
        this.degree = degree;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Burrito{");
        sb.append("degree=").append(degree);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(new Burrito(Spiciness.NOT));
        System.out.println(new Burrito(Spiciness.MEDIUM));
        System.out.println(new Burrito(Spiciness.HOT));

//        Burrito{degree=NOT}
//        Burrito{degree=MEDIUM}
//        Burrito{degree=HOT}
    }
}
