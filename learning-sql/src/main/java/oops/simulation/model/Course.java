/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation.model;

import oops.simulation.annotation.Count;
import oops.simulation.annotation.ValueRange;
import oops.simulation.enums.CourseNameEnum;
import oops.simulation.enums.CoursePeriodEnum;
import oops.simulation.enums.CourseTimeEnum;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : Course.java
 */
@Count(300)
public class Course extends BaseEntity
{
    @ValueRange(CourseNameEnum.class)
    protected String name;

    @ValueRange(CourseTimeEnum.class)
    protected String time;

    @ValueRange(CoursePeriodEnum.class)
    protected String period;

    protected int teacherId;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getPeriod()
    {
        return period;
    }

    public void setPeriod(String period)
    {
        this.period = period;
    }

    public int getTeacherId()
    {
        return teacherId;
    }

    public void setTeacherId(int teacherId)
    {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Course))
        {
            return false;
        }

        Course course = (Course) o;

        if (name != null ? !name.equals(course.name) : course.name != null)
        {
            return false;
        }
        if (time != null ? !time.equals(course.time) : course.time != null)
        {
            return false;
        }
        return period != null ? period.equals(course.period) : course.period == null;

    }

    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (period != null ? period.hashCode() : 0);
        return result;
    }
}
