package com.sap.refactoring.users;

import lombok.Data;

import java.util.List;

@Data
public class User
{
	String name;
	String email;
	List<String> roles;
}
