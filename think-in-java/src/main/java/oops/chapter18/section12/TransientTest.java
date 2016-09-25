/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section12;

import java.io.*;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-15
 * Project        : desultory-essay
 * File Name      : Transient.java
 */
public class TransientTest
{
    public static void main(String[] args)
    {
        try
        {
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(baout);

            Tip tip = new Tip("content_string", 12345, "password_string", 3765327);
            System.out.println(tip);
            // Tip{content='content_string', intValue=12345, password='password_string', secretFactor=3765327}

            out.writeObject(tip);

            ByteArrayInputStream bain = new ByteArrayInputStream(baout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bain);
            Tip tip1 = (Tip) in.readObject();

            in.close();
            out.close();

            System.out.println(tip1);
            // Tip{content='content_string', intValue=12345, password='null', secretFactor=0}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}

class Tip implements Serializable
{
    private String content;

    private int intValue;

    private transient String password;

    private transient int secretFactor;

    public Tip()
    {
    }

    public Tip(String content, int intValue, String password, int secretFactor)
    {
        this.content = content;
        this.intValue = intValue;
        this.password = password;
        this.secretFactor = secretFactor;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getIntValue()
    {
        return intValue;
    }

    public void setIntValue(int intValue)
    {
        this.intValue = intValue;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getSecretFactor()
    {
        return secretFactor;
    }

    public void setSecretFactor(int secretFactor)
    {
        this.secretFactor = secretFactor;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Tip{");
        sb.append("content='").append(content).append('\'');
        sb.append(", intValue=").append(intValue);
        sb.append(", password='").append(password).append('\'');
        sb.append(", secretFactor=").append(secretFactor);
        sb.append('}');
        return sb.toString();
    }
}
