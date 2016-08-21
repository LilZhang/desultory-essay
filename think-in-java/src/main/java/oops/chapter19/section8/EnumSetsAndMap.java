/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter19.section8;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-08-17
 * Project        : desultory-essay
 * File Name      : EnumSets.java
 */
public class EnumSetsAndMap
{
    public static void main(String[] args)
    {
        EnumSet<Direction> directions = EnumSet.noneOf(Direction.class);
        System.out.println(directions);
        // []

        directions.add(Direction.EAST);
        System.out.println(directions);
        // [EAST]

        directions.addAll(EnumSet.of(Direction.SOUTH_EAST, Direction.SOUTH_WEST, Direction.SOUTH));
        System.out.println(directions);
        // [EAST, SOUTH, SOUTH_EAST, SOUTH_WEST]

        directions = EnumSet.allOf(Direction.class);
        System.out.println(directions);
        // [EAST, WEST, SOUTH, NORTH, SOUTH_EAST, SOUTH_WEST, NORTH_EAST, NORTH_WEST]

        directions.removeAll(EnumSet.of(Direction.NORTH_EAST, Direction.NORTH_WEST));
        System.out.println(directions);
        // [EAST, WEST, SOUTH, NORTH, SOUTH_EAST, SOUTH_WEST]

        directions.removeAll(EnumSet.range(Direction.SOUTH, Direction.SOUTH_WEST));
        System.out.println(directions);
        // [EAST, WEST]

        directions = EnumSet.complementOf(directions);
        System.out.println(directions);
        // [SOUTH, NORTH, SOUTH_EAST, SOUTH_WEST, NORTH_EAST, NORTH_WEST]

        EnumMap<Direction, String> directionStringEnumMap = new EnumMap<Direction, String>(Direction.class);
        directionStringEnumMap.put(Direction.EAST, "east");
    }

    enum Direction
    {
        EAST(1, "east"),

        WEST(2, "west"),

        SOUTH(3, "south"),

        NORTH(4, "north"),

        SOUTH_EAST(5, "south-east"),

        SOUTH_WEST(6, "south-west"),

        NORTH_EAST(7, "north-east"),

        NORTH_WEST(8, "north-west");

        private int type;

        private String message;

        Direction(int type, String message)
        {
            this.type = type;
            this.message = message;
        }

        public int getType()
        {
            return type;
        }

        public String getMessage()
        {
            return message;
        }
    }
}
