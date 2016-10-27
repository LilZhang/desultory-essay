/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-27
 * Project        : desultory-essay
 * File Name      : App20.java
 */
public class App20
{
    private static final String SERIALIZABLE_PATH = "/home/lilzhang/Desktop/seriobj.data";

    private static final String EXTERNALIZABLE_PATH = "/home/lilzhang/Desktop/exterobj.data";

    public static void main(String[] args)
    {
        SerializableObj serializableObj = new SerializableObj();
        serializableObj.setContent("seri-content");
        serializableObj.setDoubleValue(13.726d);
        serializableObj.setTransiented("seri-transient");
        serializableObj.setAnotherTransiented("seri-another-transient");
        writeAndReadSeriObj(serializableObj, SERIALIZABLE_PATH);

        ExternalizableObj externalizableObj = new ExternalizableObj();
        externalizableObj.setContent("exter-content");
        externalizableObj.setDoubleValue(84.943d);
        externalizableObj.setTransiented("exter-transient");
        externalizableObj.setAnotherTransiented("exter-another-transient");
        writeAndReadSeriObj(externalizableObj, EXTERNALIZABLE_PATH);
    }

    private static void writeAndReadSeriObj(Object object, String outputPath)
    {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try
        {
            FileOutputStream fout = new FileOutputStream(outputPath);
            out = new ObjectOutputStream(fout);

            out.writeObject(object);

            FileInputStream fin = new FileInputStream(outputPath);
            in = new ObjectInputStream(fin);
            Object object1 = in.readObject();
            System.out.println(object1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}

class SerializableObj implements Serializable
{
    public static int staticValue = 0;

    private String content;

    private double doubleValue;

    private transient String transiented;

    private transient String anotherTransiented;

    public SerializableObj()
    {
        ++staticValue;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public double getDoubleValue()
    {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue)
    {
        this.doubleValue = doubleValue;
    }

    public String getTransiented()
    {
        return transiented;
    }

    public void setTransiented(String transiented)
    {
        this.transiented = transiented;
    }

    public String getAnotherTransiented()
    {
        return anotherTransiented;
    }

    public void setAnotherTransiented(String anotherTransiented)
    {
        this.anotherTransiented = anotherTransiented;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException
    {
        stream.defaultWriteObject();
        stream.writeUTF(this.anotherTransiented);
        stream.writeInt(staticValue);
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
    {
        stream.defaultReadObject();
        this.anotherTransiented = stream.readUTF();
        staticValue = stream.readInt();
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("SerializableObj{");
        sb.append("content='").append(content).append('\'');
        sb.append(", doubleValue=").append(doubleValue);
        sb.append(", transiented='").append(transiented).append('\'');
        sb.append(", anotherTransiented='").append(anotherTransiented).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

class ExternalizableObj implements Externalizable
{
    public static int staticValue = 0;

    private String content;

    private double doubleValue;

    private transient String transiented;

    private transient String anotherTransiented;

    public ExternalizableObj()
    {
        ++staticValue;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public double getDoubleValue()
    {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue)
    {
        this.doubleValue = doubleValue;
    }

    public String getTransiented()
    {
        return transiented;
    }

    public void setTransiented(String transiented)
    {
        this.transiented = transiented;
    }

    public String getAnotherTransiented()
    {
        return anotherTransiented;
    }

    public void setAnotherTransiented(String anotherTransiented)
    {
        this.anotherTransiented = anotherTransiented;
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeUTF(this.content);
        out.writeDouble(this.doubleValue);
        out.writeUTF(this.anotherTransiented);
        out.writeInt(staticValue);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        setContent(in.readUTF());
        setDoubleValue(in.readDouble());
        setAnotherTransiented(in.readUTF());
        staticValue = in.readInt();
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("ExternalizableObj{");
        sb.append("content='").append(content).append('\'');
        sb.append(", doubleValue=").append(doubleValue);
        sb.append(", transiented='").append(transiented).append('\'');
        sb.append(", anotherTransiented='").append(anotherTransiented).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
