package com.sap.refactoring.web.controller;

import java.util.ArrayList;
import java.util.List;

import com.sap.refactoring.users.UserDto;
import com.sap.refactoring.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sap.refactoring.users.User;
import com.sap.refactoring.users.UserDao;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
		User user = mapToEntity(dto);
		User created = userService.createUser(user);
		return ResponseEntity.ok(mapToDto(created));
	}

	@PutMapping
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto) {
		User user = mapToEntity(dto);
		User updated = userService.updateUser(user);
		return ResponseEntity.ok(mapToDto(updated));
	}

	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deleteUser(@PathVariable String email) {
		userService.deleteUser(email);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {
		List<UserDto> users = userService.getUsers()
				.stream()
				.map(this::mapToDto)
				.toList();

		return ResponseEntity.ok(users);
	}

	@GetMapping("/{email}")
	public ResponseEntity<UserDto> getUser(@PathVariable String email) {
		User user = userService.findUser(email);
		return ResponseEntity.ok(mapToDto(user));
	}


	private User mapToEntity(UserDto dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setRoles(dto.getRoles());
		return user;
	}

	private UserDto mapToDto(User user) {
		UserDto dto = new UserDto();
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setRoles(user.getRoles());
		return dto;
	}
}
