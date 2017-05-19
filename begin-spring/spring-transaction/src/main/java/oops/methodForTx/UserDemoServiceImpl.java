/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.methodForTx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-01-13
 * Project        : desultory-essay
 * File Name      : UserDemoServiceImpl.java
 */
@Service("userDemoService")
public class UserDemoServiceImpl implements UserDemoService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean increaseAge(int id, int amount)
    {
        return jdbcTemplate.update(
                "UPDATE user_demo ud SET ud.age = (ud.age + ?) WHERE ud.id = ?", amount, id)
                == 1;
    }

    public boolean decreaseAge(int id, int amount)
    {
        return jdbcTemplate.update(
                "UPDATE user_demo ud SET ud.age = (ud.age - ?) WHERE ud.id = ?", amount, id)
                == 1;
    }

    public int insert(UserDemo userDemo)
    {

        return 0;
    }

    public UserDemo loadById(int id)
    {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, age, birthday, addtime FROM user_demo WHERE id = ?",
                rowMapper, id);
    }
}
