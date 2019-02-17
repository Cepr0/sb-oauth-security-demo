package com.github.cepr0.demo.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
@DynamicUpdate
@DynamicInsert
public class User extends IntIdEntity {

	@Column(length = 64, nullable = false)
	private String name;

	@Column(length = 64, nullable = false, unique = true)
	private String email;

	@Column(length = 128, nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 5)
	private Role role = Role.USER;

	public enum Role {
		USER, ADMIN
	}
}
