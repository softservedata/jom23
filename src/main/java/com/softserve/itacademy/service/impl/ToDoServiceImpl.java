package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {

    private UserService userService;

    @Autowired
    public ToDoServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public ToDo addTodo(ToDo todo, User user) {
        if (Objects.isNull(todo)) throw new IllegalArgumentException("ToDo can not be Null");
        if (Objects.isNull(user)) throw new IllegalArgumentException("User can not be Null");
        todo.setOwner(user);
        user.getMyTodos().add(todo);
        if (userService.getAll().contains(user)) {
            userService.updateUser(user);
        } else {
            userService.addUser(user);
        }
        return todo;
    }


    public ToDo updateTodo(ToDo todo) {
        if (Objects.isNull(todo)) throw new IllegalArgumentException("ToDo can not be Null");
        for (User user : userService.getAll()
        ) {
            if (todo.getOwner().equals(user)) {
                user.getMyTodos().removeIf(toDo1 -> toDo1.getTitle().equals(todo.getTitle()));
                user.getMyTodos().add(todo);
            }
        }
        return todo;
    }

    public void deleteTodo(ToDo todo) {
        if (Objects.isNull(todo)) throw new IllegalArgumentException("ToDo can not be Null");
        for (User user : userService.getAll()
        ) {
            if (todo.getOwner().equals(user)) {
                user.getMyTodos().removeIf(toDo1 -> toDo1.getTitle().equals(todo.getTitle()));
            }
        }
    }

    public List<ToDo> getAll() {
        return userService.getAll().stream()
                .flatMap(list -> list.getMyTodos()
                        .stream()).collect(Collectors.toList());
    }

    public List<ToDo> getByUser(User user) {
        if (Objects.isNull(user)) throw new IllegalArgumentException("User can not be Null");
        return userService.getAll().stream().flatMap(list -> list.getMyTodos()
                .stream()).filter(toDo -> toDo.getOwner().equals(user))
                .collect(Collectors.toList());
    }

    public ToDo getByUserTitle(User user, String title) {
        if (Objects.isNull(user)) throw new IllegalArgumentException("User can not be Null");
        if (Objects.isNull(title)) throw new IllegalArgumentException("Title can not be Null");
        if (title.isEmpty()) throw new IllegalArgumentException("Title should not be empty");
        return getByUser(user)
                .stream()
                .filter(toDo -> toDo.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ToDo with title: " + title + " not found"));

    }

}
