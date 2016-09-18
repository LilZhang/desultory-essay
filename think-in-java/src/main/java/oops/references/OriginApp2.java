/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.*;
import java.util.*;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-09-16
 * Project        : desultory-essay
 * File Name      : App2.java
 */
public class OriginApp2
{

    public static void main(String[] args) throws Exception
    {
        try
        {
            operateEnum(Direction.class, StorageException.class);
        }
        catch (StorageException e)
        {
            throw e;
        }

        try
        {
            operateEnum(Direction.class, StorageException.class);
        }
        catch (StorageException e)
        {
            throw ((Exception) new Exception().initCause(e));
        }

        System.out.println("...\n");

        try
        {
            operateEnum(Direction.class, StorageException.class);
        }
        catch (StorageException e)
        {
            throw new IllegalArgumentException();
        }

        try
        {
            operateEnum(Direction.class, StorageException.class);
        }
        catch (StorageException e)
        {
            throw ((Exception) e.fillInStackTrace());
        }
    }

    public static <T extends Enum<T>, E extends Exception> void operateEnum(Class<T> clazz, Class<E> exceptionClass) throws E
    {
        Storage<T> storage = new Storage<T>(clazz);
        StorageList<T> storageList = storage.getStorage();
        storageList.addAll(Arrays.asList(clazz.getEnumConstants()));

        StorageList<T> storageList2 = new StorageList<T>();
        for (T t : storageList.reverse())
        {
            storageList2.add(t);
        }

        storageList.clear();
        for (T t : storageList2)
        {
            storageList.add(t);
        }

        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try
        {
            FileOutputStream fileOutputStream =
                    new FileOutputStream("E:\\DESKTOP\\storage1.out");
            out = new ObjectOutputStream(fileOutputStream);

            out.writeObject(storage);
            out.flush();

            FileInputStream fileInputStream =
                    new FileInputStream("E:\\DESKTOP\\storage1.out");
            in = new ObjectInputStream(fileInputStream);
            Storage<T> storage1 = (Storage<T>) in.readObject();
            System.out.println();
        }
        catch (Exception e)
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
                catch (Exception e)
                {
                    // ignore
                }
            }

            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (Exception e)
                {
                    // ignore
                }
            }
        }

        try
        {
            ByteArrayOutputStream byteArrayOutputStream =
                    new ByteArrayOutputStream();

            out = new ObjectOutputStream(byteArrayOutputStream);
            out.writeObject(storage);
            out.flush();

            ByteArrayInputStream byteArrayInputStream =
                    new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            in = new ObjectInputStream(byteArrayInputStream);
            Storage<T> storage1 = (Storage<T>) in.readObject();
            System.out.println();
        }
        catch (Exception e)
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
                catch (Exception e)
                {
                    // ignore
                }
            }

            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (Exception e)
                {
                    // ignore
                }
            }
        }

        try
        {
            throw exceptionClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

enum Direction
{
    EAST(1, "east")
            {
                @Override
                public void info()
                {

                }
            },

    WEST(2, "west")
            {
                @Override
                public void info()
                {

                }
            },

    NORTH(3, "north")
            {
                @Override
                public void info()
                {

                }
            },

    SOUTH(4, "south")
            {
                @Override
                public void info()
                {

                }
            };

    private int type;

    private String message;

    Direction(int type, String message)
    {
        this.type = type;
        this.message = message;
    }

    public abstract void info();

    public int getType()
    {
        return type;
    }

    public String getMessage()
    {
        return message;
    }
}

class Storage<T extends Enum<T>> implements Serializable
{
    private static int counter = 0;

    private final StorageList<T> storageList = new StorageList<T>();

    private final transient String memo;

    private transient EnumMap<T, String> enumMap;   // not final

    public Storage(Class<T> clazz)
    {
        this.memo = OriginApp1.genRandomStr(10);
        this.enumMap = new EnumMap<T, String>(clazz);
        counter++;
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        out.writeObject(enumMap);
        out.writeInt(counter);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        this.enumMap = ((EnumMap<T, String>) in.readObject());
        counter = in.readInt();
    }

    public static int getCounter()
    {
        return counter;
    }

    public StorageList<T> getStorage()
    {
        return storageList;
    }

    public String getMemo()
    {
        return memo;
    }

    public EnumMap<T, String> getEnumMap()
    {
        return enumMap;
    }
}

class StorageList<T> extends ArrayList<T>
{
    public Iterable<T> reverse()
    {
        return new Iterable<T>()
        {
            public Iterator<T> iterator()
            {
                return new Iterator<T>()
                {
                    int cursor = size() - 1;

                    public boolean hasNext()
                    {
                        return cursor >= 0;
                    }

                    public T next()
                    {
                        return get(cursor--);
                    }

                    public void remove()
                    {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

class StorageException extends Exception
{

}
