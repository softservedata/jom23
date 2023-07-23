package com.softserve.itacademy.component.user.dto;

import com.softserve.itacademy.component.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .email(user.getEmail())
                .build();
    }

    public void fillFields(User user, UpdateUserDto updateUserDto) {
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setEmail(updateUserDto.getEmail());
    }
}
