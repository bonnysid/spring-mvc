package org.bonnysid.bloom.models.user;

import org.bonnysid.bloom.config.DataConfig;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static String URL = DataConfig.URL.value();
    private static String USERNAME = DataConfig.USERNAME.value();
    private static String PASSWORD = DataConfig.PASSWORD.value();

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> get() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM Users;";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                User u = new User();
                u.setID(resultSet.getInt("id"));
                u.setLogin(resultSet.getString("login"));
                u.setEmail(resultSet.getString("email"));
                u.setAge(resultSet.getInt("age"));
                users.add(u);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return users;
    }

    public User get(int id) {
        User u = null;
        try {
            String sql = "SELECT email, login, id, age FROM Users WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            u = new User();
            u.setID(resultSet.getInt("id"));
            u.setLogin(resultSet.getString("login"));
            u.setEmail(resultSet.getString("email"));
            u.setAge(resultSet.getInt("age"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return u;
    }

    public void insert(User user) {
        try {
            String sql = "INSERT INTO Users (login, email, password, age) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getAge());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM Users WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void update(int id, User newDataUser) {
        try {
            String sql = "UPDATE Users SET age=?, login=?, email=?, password=? WHERE id=?";
            PreparedStatement statement =connection.prepareStatement(sql);
            statement.setInt(1,newDataUser.getAge());
            statement.setString(2,newDataUser.getLogin());
            statement.setString(3,newDataUser.getEmail());
            statement.setString(4,newDataUser.getPassword());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
