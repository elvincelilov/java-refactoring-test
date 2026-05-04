package com.sap.refactoring.users;

import com.sap.refactoring.exception.DuplicateEmailException;
import com.sap.refactoring.exception.InvalidUserException;
import com.sap.refactoring.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        validateUser(user);

        if (userRepository.existsById(user.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        validateUser(user);

        User existing = userRepository.findById(user.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        existing.setName(user.getName());
        existing.setRoles(user.getRoles());

        return userRepository.save(existing);
    }

    @Transactional
    public void deleteUser(String email) {
        if (!userRepository.existsById(email)) {
            throw new UserNotFoundException("User not found");
        }

        userRepository.deleteById(email);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findUser(String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private void validateUser(User user) {

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new InvalidUserException("Name is required");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            throw new InvalidUserException("Invalid email");
        }

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new InvalidUserException("User must have at least one role");
        }
    }

    public User mapToEntity(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRoles(dto.getRoles());
        return user;
    }

    public UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        return dto;
    }
}
