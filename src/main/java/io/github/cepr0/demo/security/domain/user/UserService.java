package io.github.cepr0.demo.security.domain.user;

import io.github.cepr0.crud.service.CrudService;
import io.github.cepr0.demo.security.domain.user.dto.UserRequest;
import io.github.cepr0.demo.security.domain.user.dto.UserResponse;
import io.github.cepr0.demo.security.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends CrudService<User, Integer, UserRequest, UserResponse>, UserDetailsService {
}
