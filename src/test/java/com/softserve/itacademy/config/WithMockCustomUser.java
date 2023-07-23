package com.softserve.itacademy.config;

import com.softserve.itacademy.component.user.UserRole;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = CustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
    long id() default 0;

    String firstName() default "";

    String lastName() default "";

    String email() default "";

    String password() default "";

    UserRole role() default UserRole.USER;
}
