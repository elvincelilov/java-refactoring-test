//package com.sap.refactoring.users;
//
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class UserDao
//{
//	private final List<User> users = new ArrayList<>();
//
//	public void saveUser(User user) {
//		users.add(user);
//	}
//
//	public List<User> getUsers() {
//		return new ArrayList<>(users);
//	}
//
//	public User findByEmail(String email) {
//		for (User user : users) {
//			if (user.getEmail().equals(email)) {
//				return user;
//			}
//		}
//		return null;
//	}
//
//	public void deleteByEmail(String email) {
//		users.removeIf(user -> user.getEmail().equals(email));
//	}
//
//	public void updateUser(User updatedUser) {
//		for (User user : users) {
//			if (user.getEmail().equals(updatedUser.getEmail())) {
//				user.setName(updatedUser.getName());
//				user.setRoles(updatedUser.getRoles());
//				return;
//			}
//		}
//	}
//}
