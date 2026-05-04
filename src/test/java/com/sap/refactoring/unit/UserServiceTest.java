package com.sap.refactoring.unit;

import com.sap.refactoring.exception.DuplicateEmailException;
import com.sap.refactoring.exception.InvalidUserException;
import com.sap.refactoring.users.User;
import com.sap.refactoring.users.UserRepository;
import com.sap.refactoring.users.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    void shouldCreateUserSuccessfully() {
        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@mail.com");
        user.setRoles(List.of("USER"));

        when(userRepository.existsById(user.getEmail())).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertThat(result).isNotNull();
        verify(userRepository).save(user);
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@mail.com");
        user.setRoles(List.of("USER"));

        when(userRepository.existsById(user.getEmail())).thenReturn(true);

        assertThatThrownBy(() -> userService.createUser(user))
                .isInstanceOf(DuplicateEmailException.class);
    }

    @Test
    void shouldFindUserByEmail() {
        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@mail.com");
        user.setRoles(List.of("USER"));

        when(userRepository.findById("ali@mail.com"))
                .thenReturn(java.util.Optional.of(user));

        User found = userService.findUser("ali@mail.com");

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Ali");
    }

    @Test
    void shouldThrowExceptionWhenRolesAreEmpty() {
        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@mail.com");
        user.setRoles(List.of());

        assertThatThrownBy(() -> userService.createUser(user))
                .isInstanceOf(InvalidUserException.class);
    }
}
