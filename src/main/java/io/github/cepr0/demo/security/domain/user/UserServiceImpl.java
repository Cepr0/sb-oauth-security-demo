package io.github.cepr0.demo.security.domain.user;

import io.github.cepr0.crud.service.AbstractCrudService;
import io.github.cepr0.demo.security.domain.user.dto.UserRequest;
import io.github.cepr0.demo.security.domain.user.dto.UserResponse;
import io.github.cepr0.demo.security.model.User;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.github.cepr0.demo.security.model.User.Role.ADMIN;
import static io.github.cepr0.demo.security.model.User.Role.USER;

@Service
public class UserServiceImpl extends AbstractCrudService<User, Integer, UserRequest, UserResponse> implements UserService {

	private final UserRepo userRepo;

	public UserServiceImpl(@NonNull final UserRepo repo, @NonNull final UserMapper mapper) {
		super(repo, mapper);
		userRepo = repo;
	}

	@Transactional(readOnly = true)
	@NonNull
	@Override
	public UserDetails loadUserByUsername(@NonNull final String email) throws UsernameNotFoundException {
		return userRepo.getByEmail(email)
				.map(user -> {

					String[] roles = user.getRole() == ADMIN ?
							new String[]{USER.name(), ADMIN.name()} :
							new String[]{USER.name()};

					//noinspection ConstantConditions
					return org.springframework.security.core.userdetails.User
							.builder()
							.username(user.getId().toString())
							.password(user.getPassword())
							.roles(roles)
							.build();
				})
				.orElseThrow(() -> new UsernameNotFoundException("User not found by " + email));
	}
}
