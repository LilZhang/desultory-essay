/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter19.section2;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-16
 * Project        : desultory-essay
 * File Name      : StandardEnum.java
 */

// 若反编译
// final class StandardEnum extends java.lang.Enum
// 所以 enum 不能够再继承别的类
// 因为 final ，所以也不能被继承
// 不过可以实现接口
public enum StandardEnum implements Biu
{
    // values() 和 valueOf(String name) 方法并非 java.lang.Enum 里面的方法。
    // 该方法是由编译期插入的。

    // 其实是 public static final StandardEnum EAST;
    EAST(1, "east"),

    WEST(2, "west"),

    SOUTH(3, "south"),

    NORTH(4, "north");

    private int type;

    private String message;

    StandardEnum(int type, String message)
    {
        this.type = type;
        this.message = message;
    }

    public static void main(String[] args)
    {
        for (StandardEnum standardEnum : StandardEnum.values())
        {
            System.out.println(standardEnum);

            /*StandardEnum{type=1, message='east'}
            StandardEnum{type=2, message='west'}
            StandardEnum{type=3, message='south'}
            StandardEnum{type=4, message='north'}*/
        }

        StandardEnum south = StandardEnum.SOUTH;
        switch (south)
        {
            case EAST:
                System.out.println("east");
                break;
            case WEST:
                System.out.println("west");
                break;
            case SOUTH:
                System.out.println("south");
                break;
            case NORTH:
                System.out.println("north");
                break;
            default:
                System.out.println("...");
        }

        // 怎样在 Class 对象中 取得该enum的所有实例
        for (StandardEnum standardEnum : south.getClass().getEnumConstants())
        {
            // 接口方法由实例调用
            standardEnum.doBiu();
        }
    }

    public int getType()
    {
        return type;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("StandardEnum{");
        sb.append("type=").append(type);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void doBiu()
    {
        // TODO: 2016/8/16 biu biu biu
        System.out.println("biu biu biu");
    }
}

interface Biu
{
    void doBiu();
}
