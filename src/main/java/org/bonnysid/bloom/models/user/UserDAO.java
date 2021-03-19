package org.bonnysid.bloom.models.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int COUNT_USERS = 0;
    private List<User> users = new ArrayList<>();

    {
        users.add(new User(COUNT_USERS++, "bonnysid"));
        users.add(new User(COUNT_USERS++, "midoran"));
        users.add(new User(COUNT_USERS++, "yanixxxs"));
        users.add(new User(COUNT_USERS++, "jojo"));
        users.add(new User(COUNT_USERS++, "custom"));
        users.add(new User(COUNT_USERS++, "geek"));
    }

    public List<User> get() {
        return users;
    }

    public User get(int id) {
        return users.stream().filter(u -> u.getID() == id).findAny().orElse(null);
    }

    public void insert(User user) {
        user.setId(COUNT_USERS++);
        users.add(user);
    }

    public void delete(int id) {
        users.removeIf(p -> p.getID() == id);
    }
}
