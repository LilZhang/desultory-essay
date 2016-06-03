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

        public void event() throws JuniorException
        {

        }

        public abstract void atBat() throws JuniorException, SeniorException;

        public void duplicateMethod() throws ExtendedJuniorException
        {

        }
    }

    public interface Interface
    {
        void interfaceMethod();

        void interfaceMethodThrowable() throws SeniorException;

        void duplicateMethod() throws ExtendedSeniorException;
    }

    public static class ExtendedObj extends Obj implements Interface
    {
        // 父类构造器异常须保留(可以由父类异常代替)，还可以添加别的异常
        // 子类构造器不能捕获到父类构造器的异常
        public ExtendedObj() throws AdditionSeniorException, ExtendedJuniorException
        {
        }

        // 父类：throw A
        // 子类: throw A, or throw ExtendsA , or throw nothing
        @Override
        public void event() throws ExtendedJuniorException
        {
//            super.event();
        }

        // 父类方法若无抛出异常，子类也无法抛出异常
        @Override
        public void atBat() throws ExtendedSeniorException
        {

        }

        // 接口若无抛出异常，其实现类也无法抛出异常
        public void interfaceMethod()
        {

        }

        // 接口：throw A
        // 实现: throw A, or throw ExtendsA , or throw nothing
        public void interfaceMethodThrowable() throws ExtendedSeniorException
        {

        }

        // 父类与接口的duplicate方法
        // 可以不抛出任何异常
        @Override
        public void duplicateMethod()
        {
//            super.duplicateMethod();
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
