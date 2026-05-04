package com.sap.refactoring.users;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name="users")
@Entity
public class User
{
	@Id
	private String email;

	private String name;

	@ElementCollection
	@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_email"))
	@Column(name = "role")
	private List<String> roles;
}
