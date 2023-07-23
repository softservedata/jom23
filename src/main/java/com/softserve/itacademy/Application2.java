package com.softserve.itacademy;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Application2 {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = annotationConfigContext.getBean(UserService.class);
        ToDoService toDoService = annotationConfigContext.getBean(ToDoService.class);
        /*
        User user = new User("Mike", "Pears", "coc@ga", "12345", new ArrayList<>());
        ToDo toDo = new ToDo("Chill on the beach", LocalDateTime.now(), user, new ArrayList<>());

        userService.addUser(user);
        toDoService.addTodo(toDo, user);

        System.out.println(userService.getAll().size());
        System.out.println(toDoService.getAll().size());
        toDoService.deleteTodo(toDo);
        System.out.println(userService.getAll().size());
        System.out.println(toDoService.getAll().size());

        annotationConfigContext.close();
        */
    }
}
