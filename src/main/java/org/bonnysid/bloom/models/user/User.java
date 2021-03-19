package org.bonnysid.bloom.models.user;

public class User {
    private int id;
    private String login;

    public User() {}

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public void setId(int id) {
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
                '}';
    }
}
