/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation.model;

import oops.simulation.annotation.Count;
import oops.simulation.annotation.ValueRange;
import oops.simulation.enums.*;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : Student.java
 */
@Count(2500)
public class Student extends BaseEntity
{
    protected String firstName;

    protected String lastName;

    @ValueRange(GenderEnum.class)
    protected String gender;

    @ValueRange(StudentAgeEnum.class)
    protected int age;

    @ValueRange(StudentGradeEnum.class)
    protected int grade;

    @ValueRange(CountryEnum.class)
    protected String nationality;

    protected int classId;

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

    public int getGrade()
    {
        return grade;
    }

    public void setGrade(int grade)
    {
        this.grade = grade;
    }

    public String getNationality()
    {
        return nationality;
    }

    public void setNationality(String nationality)
    {
        this.nationality = nationality;
    }

    public int getClassId()
    {
        return classId;
    }

    public void setClassId(int classId)
    {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Student))
        {
            return false;
        }

        Student student = (Student) o;

        if (age != student.age)
        {
            return false;
        }
        if (grade != student.grade)
        {
            return false;
        }
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null)
        {
            return false;
        }
        if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null)
        {
            return false;
        }
        if (gender != null ? !gender.equals(student.gender) : student.gender != null)
        {
            return false;
        }
        return nationality != null ? nationality.equals(student.nationality) : student.nationality == null;

    }

    @Override
    public int hashCode()
    {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + grade;
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        return result;
    }
}
