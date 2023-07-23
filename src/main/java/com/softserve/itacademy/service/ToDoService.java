package com.softserve.itacademy.service;

import java.util.List;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;

public interface ToDoService {
    
    ToDo addTodo(ToDo todo, User userOwner);

    ToDo updateTodo(int idToDo, ToDo todo);

    void deleteTodo(ToDo todo);

    void deleteTodoById(int idToDo);

    ToDo getToDoById(int idToDo);

    List<ToDo> getAll();

    List<ToDo> getByUser(User userOwner);

    List<ToDo> getByUserAndToDoTitle(User userOwner, String todoTitle);
}
