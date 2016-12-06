/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation;

import oops.simulation.model.*;
import oops.simulation.model.Class;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-25
 * Project        : desultory-essay
 * File Name      : DataObject.java
 */
public class DataObject implements Serializable
{
    public static final long serialVersionUID = 1;

    private List<Student> studentList;

    private List<oops.simulation.model.Class> classList;

    private List<Major> majorList;

    private List<Teacher> teacherList;

    private List<Course> courseList;

    private List<StudentCourse> studentCourseList;

    public List<Student> getStudentList()
    {
        return studentList;
    }

    public void setStudentList(List<Student> studentList)
    {
        this.studentList = studentList;
    }

    public List<Class> getClassList()
    {
        return classList;
    }

    public void setClassList(List<Class> classList)
    {
        this.classList = classList;
    }

    public List<Major> getMajorList()
    {
        return majorList;
    }

    public void setMajorList(List<Major> majorList)
    {
        this.majorList = majorList;
    }

    public List<Teacher> getTeacherList()
    {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList)
    {
        this.teacherList = teacherList;
    }

    public List<Course> getCourseList()
    {
        return courseList;
    }

    public void setCourseList(List<Course> courseList)
    {
        this.courseList = courseList;
    }

    public List<StudentCourse> getStudentCourseList()
    {
        return studentCourseList;
    }

    public void setStudentCourseList(List<StudentCourse> studentCourseList)
    {
        this.studentCourseList = studentCourseList;
    }
}
