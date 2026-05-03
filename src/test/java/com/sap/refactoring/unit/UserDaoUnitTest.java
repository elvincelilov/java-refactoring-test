package com.sap.refactoring.unit;

import java.util.Arrays;
import java.util.List;

import com.sap.refactoring.web.controller.UserController;
import org.junit.jupiter.api.Test;

import com.sap.refactoring.users.User;
import com.sap.refactoring.users.UserDao;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoUnitTest
{
	@Test
	void shouldSaveUser() {
		UserDao dao = new UserDao();

		User user = new User();
		user.setName("Ali");
		user.setEmail("ali@mail.com");
		user.setRoles(List.of("USER"));

		dao.saveUser(user);

		assertThat(dao.getUsers()).hasSize(1);
	}

	@Test
	void shouldFindUserByEmail() {
		UserDao dao = new UserDao();

		User user = new User();
		user.setName("Ali");
		user.setEmail("ali@mail.com");
		user.setRoles(List.of("USER"));

		dao.saveUser(user);

		User found = dao.findByEmail("ali@mail.com");

		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Ali");
	}

	@Test
	void shouldDeleteUser() {
		UserDao dao = new UserDao();

		User user = new User();
		user.setName("Ali");
		user.setEmail("ali@mail.com");
		user.setRoles(List.of("USER"));

		dao.saveUser(user);
		dao.deleteByEmail("ali@mail.com");

		assertThat(dao.getUsers()).isEmpty();
	}
}
