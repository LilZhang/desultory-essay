/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.jdbc;

import oops.model.UserDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-12
 * Project        : desultory-essay
 * File Name      : JdbcExecuteBean.java
 */
@Service
public class JdbcExecuteBean
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<UserDemo> rowMapper = new RowMapper<UserDemo>()
    {
        public UserDemo mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            UserDemo userDemo = new UserDemo();
            userDemo.setId(rs.getInt("id"));
            userDemo.setName(rs.getString("name"));
            userDemo.setAge(rs.getInt("age"));
            userDemo.setBirthday(rs.getDate("birthday"));
            userDemo.setAddtime(rs.getTimestamp("addtime"));
            return userDemo;
        }
    };

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDemo loadById(int id)
    {
        return jdbcTemplate.queryForObject("SELECT id, name, age, birthday, addtime FROM user_demo WHERE id = ?",
               rowMapper , id);
    }

    public List<UserDemo> loadByAge(int age)
    {
        return jdbcTemplate.query("SELECT id, name, age, birthday, addtime FROM user_demo WHERE age = ?",
                rowMapper, age);
    }

    public List<UserDemo> loadByName(String name)
    {
        return namedParameterJdbcTemplate.query(
                "SELECT id, name, age, birthday, addtime FROM user_demo WHERE name LIKE '%:name%'",
                Collections.singletonMap("name", name), rowMapper);
    }

    public List<UserDemo> loadByIds(List<Integer> ids)
    {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("query_ids", ids);
        return namedParameterJdbcTemplate.query(
                "SELECT id, name, age, birthday, addtime FROM user_demo WHERE id IN (:query_ids)",
                sqlParameterSource, rowMapper);
    }
}
