package com.softserve.itacademy;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TaskServiceTest {
    private static TaskService taskService;

    @Mock
    private Task task;

    @Mock
    private ToDo toDo;

    @Mock
    private ToDoService toDoService;

    @Mock
    private User user;

    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        taskService = annotationConfigContext.getBean(TaskService.class);
        annotationConfigContext.close();
    }

    @Test
    void addTask() {
        when(toDo.getTasks()).thenReturn(new ArrayList<>());
        Task actual = taskService.addTask(task, toDo);
        Assertions.assertEquals(task, actual, "The expected task is different from the Actual");
    }

    @Test
    void updateTask() {
        Task taskTest = new Task("", Priority.HIGH);
        when(toDoService.getAll()).thenReturn(Collections.singletonList(toDo));
        when(toDo.getTasks()).thenReturn(Collections.singletonList(taskTest));
        Task expected = new Task("", Priority.LOW);
        Task actual = taskServiceImpl.updateTask(expected);
        Assertions.assertEquals(expected, actual, "The expected task is different from the Actual");

    }

    @Test
    void deleteTask() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(toDo.getTasks()).thenReturn(tasks);
        when(toDoService.getAll()).thenReturn(Collections.singletonList(toDo));
        taskServiceImpl.deleteTask(task);
        Assertions.assertFalse(taskServiceImpl.getAll().contains(task), "Task are not deleted");
    }

    @Test
    void getAll() {
        taskServiceImpl.addTask(task, toDo);
        List<Task> expected = Collections.singletonList(task);
        when(toDo.getTasks()).thenReturn(expected);
        when(toDoService.getAll()).thenReturn(Collections.singletonList(toDo));
        List<Task> actual = taskServiceImpl.getAll();
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray(), "Tasks list is not correct");
    }

    @Test
    void getByToDo() {
        List<Task> expected = Collections.singletonList(task);
        when(toDo.getTasks()).thenReturn(expected);
        List<Task> actual = taskServiceImpl.getByToDo(toDo);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray(), "Tasks list is not correct");
    }

    @Test
    void getByToDoName() {
        when(task.getName()).thenReturn("Mokito");
        when(toDo.getTasks()).thenReturn(Collections.singletonList(task));
        when(toDoService.getAll()).thenReturn(Collections.singletonList(toDo));
        Task actual = taskServiceImpl.getByToDoName(toDo, "Mokito");
        Assertions.assertEquals(task, actual, "The expected task is different from the Actual");
    }

    @Test
    void getByUserName() {
        when(task.getName()).thenReturn("Mokito");
        when(toDo.getTasks()).thenReturn(Collections.singletonList(task));
        when(toDoService.getByUser(user)).thenReturn(Collections.singletonList(toDo));
        Task actual = taskServiceImpl.getByUserName(user, "Mokito");
        Assertions.assertEquals(task, actual, "The expected task is different from the Actual");
    }
}
