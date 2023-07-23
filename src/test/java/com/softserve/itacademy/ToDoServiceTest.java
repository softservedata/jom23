package com.softserve.itacademy;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitPlatform.class)
public class ToDoServiceTest {
    private static ToDoService toDoService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        annotationConfigContext.close();
    }

    @Test
    public void checkAddToDo() {

        User user = new User("firstName", "lastName", "email", "pass", new ArrayList<>());
        Task task1 = new Task("Task1", Priority.HIGH);
        Task task2 = new Task("Task2", Priority.LOW);
        Task task3 = new Task("Task3", Priority.HIGH);
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        ToDo toDo = new ToDo("Title", user, list);
        toDoService.addTodo(toDo, user);
        Assertions.assertTrue(toDoService.getByUser(user).contains(toDo), "check message");
    }

    @Test
    public void checkUpdateToDo() {
        User user = new User("firstName", "lastName", "email", "pass", new ArrayList<>());
        Task task1 = new Task("Task1", Priority.HIGH);
        Task task2 = new Task("Task2", Priority.LOW);
        Task task3 = new Task("Task3", Priority.HIGH);
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        ToDo toDo = new ToDo("Title", user, list);
        toDoService.addTodo(toDo, user);
        Task task4 = new Task("Task4", Priority.MEDIUM);
        Task task5 = new Task("Task5", Priority.LOW);
        List<Task> updatedList = new ArrayList<>();
        updatedList.add(task4);
        updatedList.add(task5);
        ToDo updatedToDo = new ToDo("UpdatedTitle", user, updatedList);
        toDoService.updateTodo(updatedToDo);
        Assertions.assertTrue(toDoService.getByUser(user).contains(updatedToDo), "check message");
    }

    @Test
    public void checkDeleteTodo() {
        User user = new User("firstName", "lastName", "email", "pass", new ArrayList<>());
        Task task1 = new Task("Task1", Priority.HIGH);
        Task task2 = new Task("Task2", Priority.LOW);
        Task task3 = new Task("Task3", Priority.HIGH);
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        ToDo toDo = new ToDo("Title", user, list);
        toDoService.addTodo(toDo, user);
        toDoService.deleteTodo(toDo);
        Assertions.assertTrue(!toDoService.getByUser(user).contains(toDo), "check message");

    }

    @Test
    public void checkGetByUser() {
        User user = new User("firstName", "lastName", "email", "pass", new ArrayList<>());
        Task task1 = new Task("Task1", Priority.HIGH);
        Task task2 = new Task("Task2", Priority.LOW);
        Task task3 = new Task("Task3", Priority.HIGH);
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        ToDo toDo = new ToDo("Title", user, list);
        toDoService.addTodo(toDo, user);
        User otherUser = new User("otherFirstName", "otherLastName", "22email", "pass22", new ArrayList<>());
        Task task4 = new Task("Task4", Priority.MEDIUM);
        Task task5 = new Task("Task5", Priority.LOW);
        List<Task> otherList = new ArrayList<>();
        otherList.add(task4);
        otherList.add(task5);
        ToDo otherToDo = new ToDo("OtherTitle", user, otherList);
        toDoService.addTodo(otherToDo, otherUser);
        Assertions.assertTrue(!toDoService.getByUser(user).contains(otherToDo), "The expected list is different from the Actual");
        Assertions.assertTrue(!toDoService.getByUser(otherUser).contains(toDo), "The expected list is different from the Actual");

    }

    @Test
    public void checkGetByUserTitle() {

        User user = new User("firstName", "lastName", "email", "pass", new ArrayList<>());
        Task task1 = new Task("Task1", Priority.HIGH);
        Task task2 = new Task("Task2", Priority.LOW);
        Task task3 = new Task("Task3", Priority.HIGH);
        List<Task> list = new ArrayList<>();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        ToDo firstToDo = new ToDo("First ToDo Title", user, list);
        Task task4 = new Task("Task4", Priority.MEDIUM);
        Task task5 = new Task("Task5", Priority.LOW);
        List<Task> otherList = new ArrayList<>();
        otherList.add(task4);
        otherList.add(task5);
        ToDo secondToDo = new ToDo("Second ToDo Title", user, otherList);
        toDoService.addTodo(firstToDo, user);
        toDoService.addTodo(secondToDo, user);
        ToDo firstResultToDo = toDoService.getByUserTitle(user, "First ToDo Title");
        ToDo secondResultToDo = toDoService.getByUserTitle(user, "Second ToDo Title");
        Assertions.assertEquals(firstToDo.getTitle(), firstResultToDo.getTitle(), "The expected ToDo has different Title ");
        Assertions.assertEquals(secondToDo, secondResultToDo, "The expected ToDo's is not equal");

    }


}
