package org.bonnysid.bloom.models.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int COUNT_USERS = 0;
    private List<User> users = new ArrayList<>();

    {
        users.add(new User(COUNT_USERS++, "bonnysid", "example@email.com", 18));
        users.add(new User(COUNT_USERS++, "midoran", "example@email.com", 18));
        users.add(new User(COUNT_USERS++, "yanixxxs", "example@email.com", 18));
        users.add(new User(COUNT_USERS++, "jojo", "example@email.com", 18));
        users.add(new User(COUNT_USERS++, "custom", "example@email.com", 18));
        users.add(new User(COUNT_USERS++, "geek", "example@email.com", 18));
    }

    public List<User> get() {
        return users;
    }

    public User get(int id) {
        return users.stream().filter(u -> u.getID() == id).findAny().orElse(null);
    }

    public void insert(User user) {
        user.setID(COUNT_USERS++);
        users.add(user);
    }

    public void delete(int id) {
        users.removeIf(p -> p.getID() == id);
    }

    public void update(int id, User newDataUser) {
        User user = get(id);
        user.setLogin(newDataUser.getLogin());
        user.setAge(newDataUser.getAge());
        user.setEmail(newDataUser.getEmail());
    }
}
