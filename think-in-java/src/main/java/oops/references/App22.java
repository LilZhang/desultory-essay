/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-10-27
 * Project        : desultory-essay
 * File Name      : App22.java
 */
public class App22
{
    public static void main(String[] args)
    {
        Direction[] enumConstants = Direction.class.getEnumConstants();

        EnumSet.of(Direction.EAST, Direction.WEST);

        EnumMap<Direction, String> enumMap = new EnumMap<Direction, String>(Direction.class);
    }

    enum Direction
    {
        EAST,

        WEST,

        NORTH,

        SOUTH;
    }
}
