package com.softserve.itacademy;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class UserServiceTest {
    private static UserService userService;

    @Mock
    private User user;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        annotationConfigContext.close();
    }

    @Test
    public void addUser() {
        User actual = userService.addUser(user);
        Assertions.assertEquals(user, actual, "The expected user is different from the Actual");
    }

    @Test
    public void updateUser() {
        User userTest = new User("", "", "", "", new ArrayList<>());
        User expected = new User("", "Mokito", "", "", new ArrayList<>());

        userService.addUser(userTest);
        User actual = userService.updateUser(expected);
        Assertions.assertEquals(expected, actual, "The expected user is different from the Actual");
    }

    @Test
    public void deleteUser() {
        userService.addUser(user);
        userService.deleteUser(user);
        Assertions.assertFalse(userService.getAll().contains(user), "User are not deleted");
    }

    @Test
    public void getAll() {
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        users.add(user);

        userService.getAll().addAll(users);
        Assertions.assertArrayEquals(users.toArray(), userService.getAll().toArray(), "Users list is not correct");
    }
}
