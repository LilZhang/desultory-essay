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
 * File Name      : SerialCtl.java
 */
public class SerialCtl implements Serializable
{
    private String notTransient;

    private transient String trans;

    public SerialCtl(String notTransient, String trans)
    {
        this.notTransient = notTransient;
        this.trans = trans;
    }

    public static void main(String[] args)
    {
        try
        {
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(baout);

            SerialCtl serialCtl = new SerialCtl("not_transient", "transient");
            System.out.println(serialCtl);
            // SerialCtl{notTransient='not_transient', trans='transient'}

            out.writeObject(serialCtl);
            out.flush();

            ByteArrayInputStream bain = new ByteArrayInputStream(baout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bain);
            SerialCtl serialCtl1 = (SerialCtl) in.readObject();
            System.out.println(serialCtl1);
            // SerialCtl{notTransient='not_transient', trans='transient'}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // 遗留的坑
    // 改变序列化方式
    private void writeObject(ObjectOutputStream stream) throws IOException
    {
        stream.defaultWriteObject();
        stream.writeObject(trans);  // 绕过了 transient
    }

    // 同上
    // 改变反序列化方式
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        stream.defaultReadObject();
        trans = ((String) stream.readObject()); // 绕过了 transient
    }

    public String getNotTransient()
    {
        return notTransient;
    }

    public void setNotTransient(String notTransient)
    {
        this.notTransient = notTransient;
    }

    public String getTrans()
    {
        return trans;
    }

    public void setTrans(String trans)
    {
        this.trans = trans;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("SerialCtl{");
        sb.append("notTransient='").append(notTransient).append('\'');
        sb.append(", trans='").append(trans).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
