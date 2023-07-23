package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final ToDoService toDoService;

    @Autowired
    public TaskServiceImpl(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public Task addTask(Task task, ToDo todo) {
        if (Objects.isNull(task)) throw new IllegalArgumentException("Task can not be Null");
        List<Task> tasks = todo.getTasks();
        if (!tasks.contains(task)){
            tasks.add(task);
            return task;
        }
        throw new IllegalArgumentException("Task with same name added to this ToDo before");
    }

    @Override
    public Task updateTask(Task task) {
        if (Objects.isNull(task)) throw new IllegalArgumentException("Task can not be Null");
        return getAll().stream()
                .filter(existTask -> existTask.getName().equals(task.getName()))
                .findFirst()
                .map(existTask -> updateExistingTask(existTask, task))
                .orElseThrow(() -> new IllegalArgumentException("Task with this name are not found"));
    }

    @Override
    public void deleteTask(Task task) {
        if (Objects.isNull(task)) throw new IllegalArgumentException("Task can not be Null");
        toDoService.getAll().forEach(todo -> todo.getTasks()
                .removeIf(existTask -> existTask.equals(task)));
    }

    @Override
    public List<Task> getAll() {
        return toDoService.getAll().stream()
                .map(ToDo::getTasks)
                .flatMap(List<Task>::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getByToDo(ToDo todo) {
        if (Objects.isNull(todo)) throw new IllegalArgumentException("ToDo can not be Null");
        return todo.getTasks();
    }

    @Override
    public Task getByToDoName(ToDo todo, String name) {
        if (Objects.isNull(todo)) throw new IllegalArgumentException("ToDo can not be Null");
        if (Objects.isNull(name)) throw new IllegalArgumentException("Name can not be Null");
        if (name.isEmpty()) throw new IllegalArgumentException("Name should not be empty");
        return toDoService.getAll().stream()
                .filter(todoExisted->todoExisted.equals(todo))
                .flatMap(toDoFound -> toDoFound.getTasks().stream())
                .filter(task -> task.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task with name: " + name + " not found"));
    }

    @Override
    public Task getByUserName(User user, String name) {
        if (Objects.isNull(user)) throw new IllegalArgumentException("User can not be Null");
        if (Objects.isNull(name)) throw new IllegalArgumentException("Name can not be Null");
        if (name.isEmpty()) throw new IllegalArgumentException("Name should not be empty");
        return toDoService.getByUser(user).stream()
                .flatMap(toDoFound-> toDoFound.getTasks().stream())
                .filter(task -> task.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Task with name: " + name + " not found"));
    }

    private Task updateExistingTask(Task taskExisting, Task taskNewInfo){
        taskExisting.setName(taskNewInfo.getName());
        taskExisting.setPriority(taskNewInfo.getPriority());
        return taskExisting;
    }

}
