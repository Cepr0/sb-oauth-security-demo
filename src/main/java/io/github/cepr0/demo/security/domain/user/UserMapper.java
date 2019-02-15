package io.github.cepr0.demo.security.domain.user;

import io.github.cepr0.crud.mapper.CrudMapper;
import io.github.cepr0.crud.mapper.ReferenceMapper;
import io.github.cepr0.demo.security.domain.user.dto.UserRequest;
import io.github.cepr0.demo.security.domain.user.dto.UserResponse;
import io.github.cepr0.demo.security.model.User;
import lombok.Getter;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
		nullValueCheckStrategy = ALWAYS,
		nullValueMappingStrategy = RETURN_DEFAULT,
		nullValuePropertyMappingStrategy = IGNORE
)
public abstract class UserMapper implements CrudMapper<User, Integer, UserRequest, UserResponse>, ReferenceMapper<User, Integer> {

	@Getter private UserRepo repo;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setRepo(@NonNull final UserRepo repo) {
		this.repo = repo;
	}

	@Autowired
	public void setPasswordEncoder(@NonNull final PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@SuppressWarnings("UnmappedTargetProperties")
	@AfterMapping
	void encodePassword(@MappingTarget @NonNull final User user, final UserRequest request) {
		if (request == null) return;
		String password = request.getPassword();
		if (password !=  null) {
			user.setPassword(passwordEncoder.encode(password));
		}
	}
}
