/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation;

import oops.simulation.enums.*;
import oops.simulation.model.*;
import oops.simulation.model.Class;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : App.java
 */
public class MainJob
{
    private static final String PATH = "/home/lilzhang/dev/data_object.bin";

    public static void main(String[] args)
    {
        DataObject dataObject = new DataObject();

        List<Major> majorList = new ArrayList<Major>();
        for (MajorNameEnum anEnum : MajorNameEnum.values())
        {
            Major major = new Major();
            major.setName(anEnum.getName());
            major.setDuration(SimUtils.random(MajorDurationEnum.class).getDuration());
            majorList.add(major);
        }
        dataObject.setMajorList(majorList);
        majorList = new ArrayList<Major>(majorList);

        List<Class> classList = new ArrayList<Class>();
        for (Major major : majorList)
        {
            for (ClassMarkEnum anEnum : ClassMarkEnum.values())
            {
                Class cls = new Class();
                cls.setMark(anEnum.getGrade());
                cls.setIsElite(SimUtils.random(ClassEliteEnum.class).getIsElite());
                cls.setMajorId(major.getId());
                classList.add(cls);
            }
        }
        dataObject.setClassList(classList);
        classList = new ArrayList<Class>(classList);

        List<Student> studentList = SimUtils.randomObjectList(Student.class);
        for (Student student : studentList)
        {
            CountryEnum countryEnum = CountryEnum.get(student.getNationality());
            student.setFirstName(SimUtils.randomName(countryEnum, student.getGender()));
            student.setLastName(SimUtils.randomName(countryEnum, student.getGender()));

            Collections.shuffle(classList);
            student.setClassId(classList.get(0).getId());
        }
        dataObject.setStudentList(studentList);
        studentList = new ArrayList<Student>(studentList);

        List<Teacher> teacherList = SimUtils.randomObjectList(Teacher.class);
        for (Teacher teacher : teacherList)
        {
            CountryEnum countryEnum = CountryEnum.get(teacher.getNationality());
            teacher.setFirstName(SimUtils.randomName(countryEnum, teacher.getGender()));
            teacher.setLastName(SimUtils.randomName(countryEnum, teacher.getGender()));

            Collections.shuffle(majorList);
            teacher.setMajorId(majorList.get(0).getId());
        }
        dataObject.setTeacherList(teacherList);
        teacherList = new ArrayList<Teacher>(teacherList);

        List<Course> courseList= SimUtils.randomObjectList(Course.class);
        for (Course course : courseList)
        {
            Collections.shuffle(teacherList);
            course.setTeacherId(teacherList.get(0).getId());
        }
        dataObject.setCourseList(courseList);
        courseList = new ArrayList<Course>(courseList);

        List<StudentCourse> studentCourseList = SimUtils.randomObjectList(StudentCourse.class);
        for (StudentCourse studentCourse : studentCourseList)
        {
            Collections.shuffle(studentList);
            studentCourse.setStudentId(studentList.get(0).getId());

            Collections.shuffle(courseList);
            studentCourse.setCourseId(courseList.get(0).getId());
        }
        dataObject.setStudentCourseList(studentCourseList);

        ObjectOutputStream out = null;

        try
        {
            out = new ObjectOutputStream(new FileOutputStream(PATH));

            out.writeObject(dataObject);
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }
    }
}
