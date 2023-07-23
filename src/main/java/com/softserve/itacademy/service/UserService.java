package com.softserve.itacademy.service;

import com.softserve.itacademy.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User updateUser(int idUser, User user);

    void deleteUser(User user);

    void deleteUserById(int idUser);

    User getUserById(int idUser);

    List<User> getAll();
}
