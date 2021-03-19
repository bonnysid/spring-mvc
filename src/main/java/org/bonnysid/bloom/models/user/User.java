package org.bonnysid.bloom.models.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {
    private int id;

    @NotEmpty(message = "Login is required")
    @Size(min = 2, max = 30, message = "Login length should be between 2-30")
    private String login;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be incorrect")
    private String email;

    @Min(value = 0, message = "Age cannot be less than 0")
    private int age;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 50, message = "Password length should be in 6-50")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(int id, String login, String email, int age) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.age = age;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                '}';
    }
}
