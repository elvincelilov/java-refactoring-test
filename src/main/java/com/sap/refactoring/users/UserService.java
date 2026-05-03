package com.sap.refactoring.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;


    @Transactional
    public User createUser(User user) {
        validateUser(user);

        if (userDao.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        userDao.saveUser(user);
        return user;
    }


    @Transactional
    public User updateUser(User user) {
        validateUser(user);

        User existing = userDao.findByEmail(user.getEmail());

        if (existing == null) {
            throw new IllegalArgumentException("User not found");
        }

        existing.setName(user.getName());
        existing.setRoles(user.getRoles());

        userDao.updateUser(existing);
        return existing;
    }

    @Transactional
    public void deleteUser(String email) {
        User existing = userDao.findByEmail(email);

        if (existing == null) {
            throw new IllegalArgumentException("User not found");
        }

        userDao.deleteByEmail(email);
    }


    public List<User> getUsers() {
        return userDao.getUsers() != null ? userDao.getUsers() : List.of();
    }


    public User findUser(String email) {
        User user = userDao.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return user;
    }


    private void validateUser(User user) {

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new IllegalArgumentException("User must have at least one role");
        }
    }
}
