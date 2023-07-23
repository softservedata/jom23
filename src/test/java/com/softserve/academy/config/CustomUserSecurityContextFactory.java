package com.softserve.academy.config;

import com.softserve.academy.model.Role;
import com.softserve.academy.model.User;
import com.softserve.academy.security.WebAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class CustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        Role role = new Role();
        role.setName(customUser.role());

        User user = new User();
        user.setId(customUser.id());
        user.setFirstName(customUser.firstName());
        user.setLastName(customUser.lastName());
        user.setEmail(customUser.email());
        user.setPassword(customUser.password());
        user.setRole(role);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        WebAuthenticationToken token = new WebAuthenticationToken(user);

        token.setAuthenticated(true);
        context.setAuthentication(token);

        return context;
    }
}
