/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section12;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-15
 * Project        : desultory-essay
 * File Name      : Worn.java
 */
public class Worn implements Serializable
{
    private static String OUT_URL = "E:\\DESKTOP\\worn.out";

    public static void main(String[] args)
    {
        try
        {
            Worn w = new Worn(6, 'a');
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(OUT_URL));

            out.writeObject("Worn storage\n");
            out.writeObject(w);
            out.close();

            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(OUT_URL));
            String s = ((String) in.readObject());
            Worn w2 = (Worn) in.readObject();
            System.out.println(s + w2);


            System.out.println();


            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            ObjectOutputStream out2 = new ObjectOutputStream(baout);

            out2.writeObject("Worn storage\n");
            out2.writeObject(w);
            out2.flush();

            ObjectInputStream in2 = new ObjectInputStream(
                    new ByteArrayInputStream(baout.toByteArray()));
            String s2 = (String) in2.readObject();
            Worn w3 = (Worn) in2.readObject();
            System.out.println(s2 + w3);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static Random random = new Random(47);

    private Data[] d = {
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10)),
            new Data(random.nextInt(10))
    };

    private Worn next;

    private char c;

    public Worn(int i, char c)
    {
        System.out.println("Worn constructor: " + i);
        this.c = c;
        if (--i > 0)
        {
            this.next = new Worn(i, (char)(c + 1));
        }
    }

    public Worn()
    {
        System.out.println("Default constructor");
    }

    public Worn(Data[] d, Worn next, char c)
    {
        this.d = d;
        this.next = next;
        this.c = c;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Worn{");
        sb.append("d=").append(Arrays.toString(d));
        sb.append(", next=").append(next);
        sb.append(", c=").append(c);
        sb.append('}');
        return sb.toString();
    }
}

class Data implements Serializable
{
    private int n;

    public Data(int n)
    {
        this.n = n;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Data{");
        sb.append("n=").append(n);
        sb.append('}');
        return sb.toString();
    }
}
