/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.jdbc;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-09
 * Project        : desultory-essay
 * File Name      : UserDemo.java
 */
public class UserDemo implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private int age;

    private Date birthday;

    private Date addTime;

    public UserDemo()
    {
    }

    public UserDemo(String name, int age, Date birthday)
    {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public Date getAddTime()
    {
        return addTime;
    }

    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("UserDemo{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", birthday=").append(birthday);
        sb.append(", addTime=").append(addTime);
        sb.append('}');
        return sb.toString();
    }
}
