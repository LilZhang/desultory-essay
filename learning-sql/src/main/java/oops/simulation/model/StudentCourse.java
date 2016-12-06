/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation.model;

import oops.simulation.annotation.Count;
import oops.simulation.annotation.ValueRange;
import oops.simulation.enums.StudentCourseScoreEnum;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : StudentCourse.java
 */
@Count(12000)
public class StudentCourse extends BaseEntity
{
    protected int studentId;

    protected int courseId;

    @ValueRange(StudentCourseScoreEnum.class)
    protected String score;

    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public int getCourseId()
    {
        return courseId;
    }

    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    public String getScore()
    {
        return score;
    }

    public void setScore(String score)
    {
        this.score = score;
    }

}
