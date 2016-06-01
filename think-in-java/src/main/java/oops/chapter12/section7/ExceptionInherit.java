/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter12.section7;

/**
 * Description:
 * <p/>
 * Create Author  : lilzhang
 * Create Date    : 2016-06-02
 * Project        : desultory-essay
 * File Name      : ExceptionInherit.java
 */
public class ExceptionInherit
{
    public abstract static class Obj
    {
        public Obj() throws ExtendedJuniorException
        {
        }

        public void event()
        {

        }

        public abstract void atBat();

        public void walk()
        {

        }
    }

    public interface Interface
    {

    }

    // FIXME: 2016/6/2 abstract to delete
    public abstract static class ExtendedObj extends Obj
    {
        // 父类构造器异常须保留(可以由父类异常代替)，还可以添加别的异常
        public ExtendedObj() throws AdditionSeniorException, ExtendedJuniorException
        {
        }
    }
}

// exceptions definition
class JuniorException extends Exception
{

}

class ExtendedJuniorException extends JuniorException
{

}

class AdditionJuniorException extends JuniorException
{

}

class SeniorException extends Exception
{

}

class ExtendedSeniorException extends SeniorException
{

}

class AdditionSeniorException extends SeniorException
{

}
