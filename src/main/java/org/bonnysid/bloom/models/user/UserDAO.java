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

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream().filter(u -> u.getID() == id).findAny().orElse(null);
    }
}
