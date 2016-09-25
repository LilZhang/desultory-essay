/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter18.section12;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * <p/>
 * Create Author  : lili.zhang
 * Create Date    : 2016-08-15
 * Project        : desultory-essay
 * File Name      : StaticSerial.java
 */

// 序列化与静态字段
public class StaticSerial
{
    private static String OUT_URL = "E:\\DESKTOP\\shape.out";

    public static void main(String[] args)
    {
        try
        {
            store();

            System.out.println();
            System.out.println();

            recover();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void store() throws IOException
    {
        // Shape Class 集合
        List<Class<? extends Shape>> shapeTypes = new ArrayList<Class<? extends Shape>>();
        shapeTypes.add(Circle.class);
        shapeTypes.add(Square.class);
        shapeTypes.add(Line.class);

        // 随机 shape 对象集合
        List<Shape> shapes = new ArrayList<Shape>();
        for (int i = 0; i < 10; i++)
        {
            shapes.add(Shape.randomFactory());
        }

        // 对象序列化前改变一下静态字段 color 的值
        for (int i = 0; i < 10; i++)
        {
            shapes.get(i).setColor(Shape.GREEN);
        }

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(OUT_URL));

        // 序列化 Shape Class 集合
        out.writeObject(shapeTypes);

        // 序列化 Line.color
        // out.writeInt(Line.color)
        Line.serializeColor(out);

        // 序列化随机 shape 对象集合
        out.writeObject(shapes);

        for (Shape shape : shapes)
        {
            System.out.println(shape);
        }

        out.close();
    }

    public static void recover() throws IOException, ClassNotFoundException
    {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(OUT_URL));

        // 反序列化 Shape Class 集合
        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in.readObject();

        // 反序列化 Line.color
        // Line.color = in.readInt()
        Line.deserilizeColor(in);

        // 反序列化随机 shape 对象集合
        List<Shape> shapes = (List<Shape>) in.readObject();

        for (Shape shape : shapes)
        {
            System.out.println(shape);
        }

        in.close();
    }

}

abstract class Shape implements Serializable
{
    public static final int RED = 1, BLUE = 2, GREEN = 3;

    private int xPos, yPos, dimension;

    private static Random random = new Random(47);

    private static int counter = 0;

    public abstract void setColor(int color);

    public abstract int getColor();

    public Shape(int xPos, int yPos, int dimension)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dimension = dimension;
    }

    public static Shape randomFactory()
    {
        int xVal = random.nextInt(100);
        int yVal = random.nextInt(100);
        int dim = random.nextInt(100);
        switch (counter++ % 3)
        {
            case 0:
                return new Circle(xVal, yVal, dim);
            case 1:
                return new Square(xVal, yVal, dim);
            case 2:
                return new Line(xVal, yVal, dim);
            default:
                throw new IllegalArgumentException("impossible");
        }
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        sb.append("\t{ color=[").append(getColor());
        sb.append("],\txPos=").append(xPos);
        sb.append(",\tyPos=").append(yPos);
        sb.append(",\tdimension=").append(dimension);
        sb.append('}');
        return sb.toString();
    }
}

// Circlr: 静态字段 color 在字段初始化时初始化
class Circle extends Shape
{
    private static int color = RED;

    public Circle(int xPos, int yPos, int dimension)
    {
        super(xPos, yPos, dimension);
    }

    @Override
    public void setColor(int color)
    {
        this.color = color;
    }

    @Override
    public int getColor()
    {
        return color;
    }
}

// Square: 静态字段 color 在构造器内初始化
class Square extends Shape
{
    private static int color;

    public Square(int xPos, int yPos, int dimension)
    {
        super(xPos, yPos, dimension);
        color = RED;
    }

    @Override
    public void setColor(int color)
    {
        this.color = color;
    }

    @Override
    public int getColor()
    {
        return color;
    }
}

// Circlr: 静态字段 color 在字段初始化时初始化。
// 但有了针对 color 的序列化与反序列化方法。
class Line extends Shape
{
    private static int color = RED;

    public Line(int xPos, int yPos, int dimension)
    {
        super(xPos, yPos, dimension);
    }

    @Override
    public void setColor(int color)
    {
        this.color = color;
    }

    @Override
    public int getColor()
    {
        return color;
    }

    // 序列化 color
    public static void serializeColor(ObjectOutputStream out) throws IOException
    {
        out.writeInt(color);
    }

    // 反序列化 color
    public static void deserilizeColor(ObjectInputStream in) throws IOException
    {
        color = in.readInt();
    }
}
