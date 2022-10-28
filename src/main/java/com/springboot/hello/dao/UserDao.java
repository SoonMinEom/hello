package com.springboot.hello.dao;


import com.springboot.hello.domain.dto.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(User user) {
        this.jdbcTemplate.update("insert into users(id, name, password) value (?,?,?)",
                user.getId(),user.getName(),user.toString());
    }

    public int deleteAll() {
        return this.jdbcTemplate.update("delete from users");
    }

    public User findById(String id) {
        String sql = "select * from users where id = ?";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
