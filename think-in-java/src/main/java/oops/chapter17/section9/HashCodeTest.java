/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter17.section9;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-07-28
 * Project        : desultory-essay
 * File Name      : HashCodeTest.java
 */
public class HashCodeTest
{
    public static void main(String[] args)
    {
        String s1 = new String("hello");
        String s2 = new String("hello");

        System.out.println(s1.hashCode());  // 99162322
        System.out.println(s2.hashCode());  // 99162322
    }

    private static class HashCodeObj
    {
        private boolean _boolean;
        private int _int;
        private long _long;
        private float _float;
        private double _double;
        private HashCodeField hashCodeField;

        @Override
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (!(o instanceof HashCodeObj))
            {
                return false;
            }

            HashCodeObj that = (HashCodeObj) o;

            if (_boolean != that._boolean)
            {
                return false;
            }
            if (_int != that._int)
            {
                return false;
            }
            if (_long != that._long)
            {
                return false;
            }
            if (Float.compare(that._float, _float) != 0)
            {
                return false;
            }
            if (Double.compare(that._double, _double) != 0)
            {
                return false;
            }
            return hashCodeField.equals(that.hashCodeField);

        }

        @Override
        public int hashCode()
        {
            int result;
            long temp;
            result = (_boolean ? 1 : 0);
            result = 31 * result + _int;
            result = 31 * result + (int) (_long ^ (_long >>> 32));
            result = 31 * result + (_float != +0.0f ? Float.floatToIntBits(_float) : 0);
            temp = Double.doubleToLongBits(_double);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + hashCodeField.hashCode();
            return result;
        }
    }

    private static class HashCodeField
    {

    }
}
