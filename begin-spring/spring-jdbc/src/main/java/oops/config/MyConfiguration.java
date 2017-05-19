/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.config;

import oops.jdbc.JdbcExecuteBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-12
 * Project        : desultory-essay
 * File Name      : MyConfiguration.java
 */
@Configuration
//@ImportResource("classpath:config/applicationContext.xml")
public class MyConfiguration
{
    private Properties config = getConfig("/home/lilzhang/Desktop/db.properties");

    // 未使用连接池，不建议在生产环境使用
    // spring-transaction 中有更好的解决方案
    @Bean
    public DataSource testDataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(config.getProperty("url"));
        dataSource.setUsername(config.getProperty("user"));
        dataSource.setPassword(config.getProperty("password"));
        return dataSource;
    }

    @Bean(destroyMethod = "close")
    public DataSource pooledDatasource()
    {
        // C3P0
        return null;
    }

    // 也可以在 xml 中配置
    @Bean
    public JdbcTemplate jdbcTemplate()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(testDataSource());
        return jdbcTemplate;
    }

    @Bean
    public JdbcExecuteBean jdbcExecuteBean()
    {
        JdbcExecuteBean jdbcExecuteBean = new JdbcExecuteBean();
        JdbcTemplate jdbcTemplate = jdbcTemplate();
        jdbcExecuteBean.setJdbcTemplate(jdbcTemplate);
        jdbcExecuteBean.setNamedParameterJdbcTemplate(new NamedParameterJdbcTemplate(jdbcTemplate));
        return jdbcExecuteBean;
    }

    private Properties getConfig(String filePath)
    {
        Properties properties = null;
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(in);
        }
        catch (IOException e)
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
        return properties;
    }
}
