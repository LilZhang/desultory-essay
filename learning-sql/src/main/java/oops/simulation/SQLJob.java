/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation;

import oops.simulation.model.Class;
import oops.simulation.model.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-25
 * Project        : desultory-essay
 * File Name      : SQLJob.java
 */
public class SQLJob
{
    private static final File FILE = new File("/home/lilzhang/Desktop/data.sql");

    private static final String DATA = "/home/lilzhang/dev/data_object.bin";

    public static void main(String[] args)
    {
        ObjectInputStream in = null;
        DataObject dataObject = null;

        try
        {
            in = new ObjectInputStream(new FileInputStream(DATA));
            dataObject = ((DataObject) in.readObject());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }

        System.out.println();
        List<String> sqls = new ArrayList<String>();
        sqls.add(SimUtils.genInsertSQL(Major.class, dataObject.getMajorList()));
        sqls.add(SimUtils.genInsertSQL(Class.class, dataObject.getClassList()));
        sqls.add(SimUtils.genInsertSQL(Student.class, dataObject.getStudentList()));
        sqls.add(SimUtils.genInsertSQL(Teacher.class, dataObject.getTeacherList()));
        sqls.add(SimUtils.genInsertSQL(Course.class, dataObject.getCourseList()));
        sqls.add(SimUtils.genInsertSQL(StudentCourse.class, dataObject.getStudentCourseList()));


        try
        {
            FileUtils.writeLines(FILE, sqls);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
