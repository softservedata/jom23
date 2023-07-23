package com.softserve.itacademy.service;

import java.util.List;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;

public interface TaskService {
    
    Task addTask(Task task, ToDo todoExist);

    Task updateTask(int idTask, Task task);

    void deleteTask(Task task);

    void deleteTaskById(int idTask);

    Task getTaskById(int idTask);

    List<Task> getAll();

    List<Task> getByToDo(ToDo todo);

    List<Task> getByToDoAndTaskName(ToDo todo, String taskName);

    List<Task> getByUserNameAndTaskName(User user, String taskName);
}
