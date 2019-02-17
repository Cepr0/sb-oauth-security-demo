package com.github.cepr0.demo.security.domain.user.dto;

import com.github.cepr0.demo.security.model.User;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static com.github.cepr0.demo.security.model.User.Role.ADMIN;
import static com.github.cepr0.demo.security.model.User.Role.USER;

@Value
@EqualsAndHashCode(callSuper = false)
public class AuthUser extends org.springframework.security.core.userdetails.User {

	private static final String ROLE_USER = "ROLE_" + USER.name();
	private static final String ROLE_ADMIN = "ROLE_" + ADMIN.name();

	private Integer userId;

	public AuthUser(User user) {
		super(
				user.getEmail(),
				user.getPassword(),
				user.getRole() == ADMIN ?
						List.of(new SimpleGrantedAuthority(ROLE_USER), new SimpleGrantedAuthority(ROLE_ADMIN)) :
						List.of(new SimpleGrantedAuthority(ROLE_USER))
		);
		userId = user.getId();
	}
}
