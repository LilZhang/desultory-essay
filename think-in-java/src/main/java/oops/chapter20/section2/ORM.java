/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.chapter20.section2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-08-17
 * Project        : desultory-essay
 * File Name      : ORM.java
 */
public interface ORM
{
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface DBTable
    {
        String name() default "";
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Constraints
    {
        boolean primaryKey() default false;
        boolean allowNull() default false;
        boolean unique() default false;
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface SQLString
    {
        int value() default 0;
        String name() default "";
        Constraints constraints() default @Constraints;
        // 默认的 (false, false, false) @Constraints
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface SQLInteger
    {
        String name () default "";
        Constraints constraints() default @Constraints;
        // 默认的 (false, false, false) @Constraints
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface ExtraParam
    {
        String[] values() default {};
        Param[] params() default {};
        ParamStatus status();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface AnotherExtraParam
    {
        Param[] value();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Param
    {
        String value() default "";
    }

    enum ParamStatus
    {
        NOT_ACTIVATED,

        ACTIVATED,

        NULL;
    }
}

// example
@ORM.DBTable(name = "table_menber")
class Member
{
    @ORM.SQLString(30)
    String firstName;

    @ORM.SQLString(50)
    String lastName;

    @ORM.SQLInteger(name = "age")
    Integer age;

    @ORM.SQLString(value = 30,
            constraints = @ORM.Constraints(primaryKey = true, unique = true))
    String handle;

    @ORM.ExtraParam(status = ORM.ParamStatus.NOT_ACTIVATED, // 若无默认值则必填
            values = {"test1", "test2"},
            params = {@ORM.Param("test3"), @ORM.Param("test4")})
    @ORM.AnotherExtraParam(
            {@ORM.Param("test5"), @ORM.Param("test6")})
    void doSomethiing()
    {
        // TODO: 2016/8/17 do something
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public String getHandle()
    {
        return handle;
    }

    public void setHandle(String handle)
    {
        this.handle = handle;
    }
}
