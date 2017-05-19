/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.methodForTx;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-12
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

    private Date addtime;

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

    public Date getAddtime()
    {
        return addtime;
    }

    public void setAddtime(Date addtime)
    {
        this.addtime = addtime;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("UserDemo{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", birthday=").append(birthday);
        sb.append(", addtime=").append(addtime);
        sb.append('}');
        return sb.toString();
    }
}
