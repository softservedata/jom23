package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private List<User> users;

    public UserServiceImpl() {
        users = new ArrayList<>();
    }

    @Override
    public User addUser(User user) {
        if (Objects.isNull(user)) throw new IllegalArgumentException("User can not be Null");
        if (!users.contains(user)) {
            users.add(user);
        }
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (Objects.isNull(user)) throw new IllegalArgumentException("User can not be Null");
        for (int i = 0; i < users.size(); i++) {
            if (user.getEmail().equals(users.get(i).getEmail())) {
                users.set(i, user);
            }
        }
        return user;
    }

    @Override
    public void deleteUser(User user) {
        if (Objects.isNull(user)) throw new IllegalArgumentException("ToDo can not be Null");
        users.remove(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

}
