package com.softserve.itacademy.service;

import com.softserve.itacademy.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    List<User> getAll();

}
