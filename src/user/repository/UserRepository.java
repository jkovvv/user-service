package user.repository;

import user.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final List<User> users = new ArrayList<>();


    public UserRepository() {

        users.add(new User(1, "Janko"));
        users.add(new User(2, "Marko"));

    }


    public List<User> findAll() {

        return users;

    }
}