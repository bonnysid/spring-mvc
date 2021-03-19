package org.bonnysid.bloom.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> get() {
        return jdbcTemplate.query("SELECT * FROM Users", new BeanPropertyRowMapper<>(User.class));
    }

    public User get(int id) {
        return jdbcTemplate.query("SELECT * FROM Users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public void insert(User user) {
        jdbcTemplate.update("INSERT INTO Users (login, email, password, age) VALUES (?, ?, ?, ?)", user.getLogin(), user.getEmail(), user.getPassword(), user.getAge());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Users WHERE id=?", id);

    }

    public void update(int id, User user) {
        jdbcTemplate.update("UPDATE Users SET login=?, email=?, password=?, age=?  WHERE id=?", user.getLogin(), user.getEmail(), user.getPassword(), user.getAge(), id);
    }
}
