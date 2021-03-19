package org.bonnysid.bloom.models.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User u = new User();
        u.setId(resultSet.getInt("id"));
        u.setLogin(resultSet.getString("login"));
        u.setEmail(resultSet.getString("email"));
        u.setAge(resultSet.getInt("age"));
        return u;
    }
}
