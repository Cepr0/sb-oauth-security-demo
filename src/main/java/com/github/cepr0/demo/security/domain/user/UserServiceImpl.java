package com.github.cepr0.demo.security.domain.user;

import com.github.cepr0.crud.service.AbstractCrudService;
import com.github.cepr0.demo.security.domain.user.dto.AuthUser;
import com.github.cepr0.demo.security.domain.user.dto.UserRequest;
import com.github.cepr0.demo.security.domain.user.dto.UserResponse;
import com.github.cepr0.demo.security.model.User;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
				.map(AuthUser::new)
				.orElseThrow(() -> new UsernameNotFoundException("[!] User not found by " + email));
	}
}
