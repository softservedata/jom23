package com.softserve.itacademy.config;

import com.softserve.itacademy.component.user.User;
import com.softserve.itacademy.component.user.UserRole;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class SpringSecurityTestConfiguration {


    @Bean("withRoleAdmin")
    @Primary
    public User testUserWithRoleAdmin() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Mike");
        user.setLastName("Green");
        user.setEmail("mike@mail.com");
        user.setPassword("1111");
        user.setRole(UserRole.ADMIN);

        return user;
    }

    @Bean("withRoleUser")
    @Primary
    public User testUserWithRoleUser() {
        User user = new User();
        user.setId(2L);
        user.setFirstName("Nick");
        user.setLastName("Brown");
        user.setEmail("nick@mail.com");
        user.setPassword("2222");
        user.setRole(UserRole.USER);

        return user;
    }
}
