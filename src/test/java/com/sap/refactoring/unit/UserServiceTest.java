package com.sap.refactoring.unit;

import com.sap.refactoring.users.User;
import com.sap.refactoring.users.UserDao;
import com.sap.refactoring.users.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UserServiceTest {

    @Test
    void shouldCreateUserSuccessfully() {
        UserDao dao = new UserDao();
        UserService service = new UserService(dao);

        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@mail.com");
        user.setRoles(List.of("USER"));

        service.createUser(user);

        assertThat(service.getUsers()).hasSize(1);
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        UserDao dao = new UserDao();
        UserService service = new UserService(dao);

        User user1 = new User();
        user1.setName("Ali");
        user1.setEmail("ali@mail.com");
        user1.setRoles(List.of("USER"));

        User user2 = new User();
        user2.setName("Veli");
        user2.setEmail("ali@mail.com");
        user2.setRoles(List.of("ADMIN"));

        service.createUser(user1);

        assertThatThrownBy(() -> service.createUser(user2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldFindUserByEmail() {
        UserDao dao = new UserDao();
        UserService service = new UserService(dao);

        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@mail.com");
        user.setRoles(List.of("USER"));

        service.createUser(user);

        User found = service.findUser("ali@mail.com");

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Ali");
    }

    @Test
    void shouldThrowExceptionWhenRolesAreEmpty() {
        UserDao dao = new UserDao();
        UserService service = new UserService(dao);

        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@mail.com");
        user.setRoles(List.of());

        assertThatThrownBy(() -> service.createUser(user))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
