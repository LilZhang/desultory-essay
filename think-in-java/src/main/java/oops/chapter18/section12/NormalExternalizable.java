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
 * File Name      : NormalExternalizable.java
 */
public class NormalExternalizable
{
    public static void main(String[] args)
    {
        try
        {
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(baout);

            Blip blip = new Blip("str", 210);
            out.writeObject(blip);
            out.flush();

            ByteArrayInputStream bain = new ByteArrayInputStream(baout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bain);
            Blip blip1 = (Blip) in.readObject();

            System.out.println(blip1);
            in.close();
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

// Serializable: 对象完全以储存的二进制为基础构造，不调用构造器
// Externalizable:  调用构造器。序列化时调用 writeExternal ， 反序列化时调用默认构造器和 readExternal 。
class Blip implements Externalizable
{
    private String string;

    private int intValue;

    //-     -//

    // 属性会被初始化
    private String originString = "str";

    private int originIntValue = 666;

    // 反序列化时，默认构造器会被调用。
    // 若默认构造器不为 public ，则抛出异常。
    public Blip()
    {
        System.out.println("default constructor");
    }

    public Blip(String string, int intValue)
    {
        this.string = string;
        this.intValue = intValue;
    }

    // writeObject 时调用
    public void writeExternal(ObjectOutput out) throws IOException
    {
        // you must do this
        // 可以手动的指定对象的哪部分被序列化
        System.out.println("writeExternal");
        out.writeObject(string);
        out.writeInt(intValue);
    }

    // readObject 时调用
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        // you must do this
        System.out.println("readExternal");
        string = (String) in.readObject();
        intValue = in.readInt();
    }

    public int getIntValue()
    {
        return intValue;
    }

    public void setIntValue(int intValue)
    {
        this.intValue = intValue;
    }

    public String getString()
    {
        return string;
    }

    public void setString(String string)
    {
        this.string = string;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Blip{");
        sb.append("string='").append(string).append('\'');
        sb.append(", intValue=").append(intValue);
        sb.append(", originString='").append(originString).append('\'');
        sb.append(", originIntValue=").append(originIntValue);
        sb.append('}');
        return sb.toString();
    }
}
