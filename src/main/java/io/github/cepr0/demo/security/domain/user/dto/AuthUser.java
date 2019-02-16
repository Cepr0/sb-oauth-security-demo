package io.github.cepr0.demo.security.domain.user.dto;

import io.github.cepr0.demo.security.model.User;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static io.github.cepr0.demo.security.model.User.Role.ADMIN;
import static io.github.cepr0.demo.security.model.User.Role.USER;

@Value
@EqualsAndHashCode(callSuper = false)
public class AuthUser extends org.springframework.security.core.userdetails.User {

	private Integer userId;

	public AuthUser(User user) {
		super(
				user.getEmail(),
				user.getPassword(),
				user.getRole() == ADMIN ?
						List.of(new SimpleGrantedAuthority(USER.name()), new SimpleGrantedAuthority(ADMIN.name())) :
						List.of(new SimpleGrantedAuthority(USER.name()))
		);
		userId = user.getId();
	}
}
