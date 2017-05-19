/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.jdbc;

import oops.config.MyConfiguration;
import oops.model.UserDemo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-12
 * Project        : desultory-essay
 * File Name      : MainJob.java
 */
public class MainJob
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MyConfiguration.class);

        JdbcExecuteBean jdbcExecuteBean = applicationContext.getBean(JdbcExecuteBean.class);
        UserDemo userDemo = jdbcExecuteBean.loadById(12);
        System.out.println(userDemo);

        System.out.println();

        List<UserDemo> userDemos = jdbcExecuteBean.loadByAge(31);
        for (UserDemo demo : userDemos)
        {
            System.out.println(demo);
        }

        System.out.println();

        List<UserDemo> userDemos1 = jdbcExecuteBean.loadByName("ham");
        for (UserDemo demo : userDemos1)
        {
            System.out.println(demo);
        }

        System.out.println();

        List<UserDemo> userDemos2 = jdbcExecuteBean.loadByIds(Arrays.asList(12, 14, 16, 18, 20));
        for (UserDemo demo : userDemos2)
        {
            System.out.println(demo);
        }
    }
}
