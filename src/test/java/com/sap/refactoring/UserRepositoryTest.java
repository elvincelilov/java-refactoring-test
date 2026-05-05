package com.sap.refactoring;

import com.sap.refactoring.users.User;
import com.sap.refactoring.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private  UserRepository userRepository;

    @Test
    void shouldSaveUser() {
        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@mail.com");
        user.setRoles(List.of("USER"));

        userRepository.save(user);

        assertThat(userRepository.findById("ali@mail.com")).isPresent();
    }

    @Test
    void shouldDeleteUser() {
        User user = new User();
        user.setName("Ali");
        user.setEmail("ali@mail.com");
        user.setRoles(List.of("USER"));

        userRepository.save(user);
        userRepository.deleteById("ali@mail.com");

        assertThat(userRepository.findById("ali@mail.com")).isEmpty();
    }
}
