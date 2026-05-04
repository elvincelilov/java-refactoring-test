package com.sap.refactoring.web.controller;

import java.util.List;

import com.sap.refactoring.users.UserDto;
import com.sap.refactoring.users.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sap.refactoring.users.User;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
		User user = userService.mapToEntity(dto);
		User created = userService.createUser(user);
		return ResponseEntity.ok(userService.mapToDto(created));
	}

	@PutMapping
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto) {
		User user = userService.mapToEntity(dto);
		User updated = userService.updateUser(user);
		return ResponseEntity.ok(userService.mapToDto(updated));
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
				.map(userService::mapToDto)
				.toList();

		return ResponseEntity.ok(users);
	}

	@GetMapping("/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
		User user = userService.findUser(email);
		return ResponseEntity.ok(userService.mapToDto(user));
	}

}
