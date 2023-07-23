package com.softserve.itacademy.component.user;

import com.softserve.itacademy.component.user.dto.UserDto;
import com.softserve.itacademy.config.exception.NullEntityReferenceException;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User expected;

    @BeforeEach
    public void setUp() {
        expected = new User();
        expected.setFirstName("Mike");
        expected.setLastName("Green");
        expected.setEmail("green@mail.com");
        expected.setPassword("1111");
    }

    @AfterEach
    public void tearDown() {
        expected = null;
    }

    @Test
    public void testCorrectCreate() {
        when(userRepository.save(expected)).thenReturn(expected);
        User actual = userService.create(expected);

        assertEquals(expected, actual);
        verify(userRepository, times(1)).save(expected);
    }

    @Test
    public void testExceptionCreate() {
        Exception exception = assertThrows(NullEntityReferenceException.class, ()
                -> userService.create(null)
        );

        assertEquals("User cannot be 'null'", exception.getMessage());
        verify(userRepository, never()).save(new User());
    }

    @Test
    public void testCorrectReadById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(expected));
        User actual = userService.readById(anyLong());

        assertEquals(expected, actual);
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testExceptionReadById() {
        Exception exception = assertThrows(EntityNotFoundException.class, ()
                -> userService.readById(anyLong())
        );

        assertEquals("User with id 0 not found", exception.getMessage());
        verify(userRepository, times(1)).findById(anyLong());
    }

//    @Test
//    public void testCorrectUpdate() {
//        when(userRepository.findById(anyLong())).thenReturn(Optional.of(expected));
//        when(userRepository.save(expected)).thenReturn(expected);
//        UserDto actual = userService.update(expected);
//
//        assertEquals(expected, actual);
//        verify(userRepository, times(1)).findById(anyLong());
//        verify(userRepository, times(1)).save(expected);
//    }

    @Test
    public void testDelete() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        doNothing().when(userRepository).delete(any(User.class));
        userService.delete(anyLong());

        verify(userRepository, times(1)).findById(anyLong());
        verify(userRepository, times(1)).delete(any(User.class));
    }

    @Test
    public void testGetAll() {
        List<User> expected = List.of(new User(), new User(), new User());

        when(userRepository.findAll()).thenReturn(expected);
        List<User> actual = userService.getAll();

        assertEquals(expected, actual);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testCorrectLoadUserByUsername() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(expected));
        UserDetails actual = userService.findByUsername(anyString()).orElseThrow();

        assertEquals(expected, actual);
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    public void testExceptionLoadUserByUsername() {
        assertThat(userService.findByUsername(anyString())).isEmpty();
        verify(userRepository, times(1)).findByEmail(anyString());
    }
}
