/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation.model;

import oops.simulation.annotation.Count;
import oops.simulation.annotation.ValueRange;
import oops.simulation.enums.CountryEnum;
import oops.simulation.enums.GenderEnum;
import oops.simulation.enums.TeacherAgeEnum;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : Teacher.java
 */
@Count(50)
public class Teacher extends BaseEntity
{
    protected String firstName;

    protected String lastName;

    @ValueRange(GenderEnum.class)
    protected String gender;

    @ValueRange(TeacherAgeEnum.class)
    protected int age;

    @ValueRange(CountryEnum.class)
    protected String nationality;

    protected int majorId;

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

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public int getMajorId()
    {
        return majorId;
    }

    public void setMajorId(int majorId)
    {
        this.majorId = majorId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Teacher))
        {
            return false;
        }

        Teacher teacher = (Teacher) o;

        if (age != teacher.age)
        {
            return false;
        }
        if (firstName != null ? !firstName.equals(teacher.firstName) : teacher.firstName != null)
        {
            return false;
        }
        if (lastName != null ? !lastName.equals(teacher.lastName) : teacher.lastName != null)
        {
            return false;
        }
        if (gender != null ? !gender.equals(teacher.gender) : teacher.gender != null)
        {
            return false;
        }
        return nationality != null ? nationality.equals(teacher.nationality) : teacher.nationality == null;

    }

    @Override
    public int hashCode()
    {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        return result;
    }
}
