package io.github.cepr0.demo.security.domain.user;

import io.github.cepr0.crud.repo.CrudRepo;
import io.github.cepr0.demo.security.model.User;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepo extends CrudRepo<User, Integer> {
	Optional<User> getByEmail(@NonNull String email);
}
