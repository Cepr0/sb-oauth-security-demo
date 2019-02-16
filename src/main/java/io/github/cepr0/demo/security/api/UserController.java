package io.github.cepr0.demo.security.api;

import io.github.cepr0.crud.controller.AbstractCrudController;
import io.github.cepr0.crud.controller.OnCreate;
import io.github.cepr0.crud.controller.OnUpdate;
import io.github.cepr0.demo.security.domain.user.UserService;
import io.github.cepr0.demo.security.domain.user.dto.AuthUser;
import io.github.cepr0.demo.security.domain.user.dto.UserRequest;
import io.github.cepr0.demo.security.domain.user.dto.UserResponse;
import io.github.cepr0.demo.security.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
public class UserController extends AbstractCrudController<User, Integer, UserRequest, UserResponse> {
	public UserController(@NonNull final UserService service) {
		super(service);
	}

	// USER-role's allowed endpoints

	@PostMapping
	@Override
	public UserResponse create(@Validated(OnCreate.class) @RequestBody @NonNull final UserRequest request) {
		return super.create(request);
	}

	@PatchMapping("/me")
	public ResponseEntity<?> updateMe(
			@NonNull final Authentication authentication,
			@Validated(OnUpdate.class) @RequestBody @NonNull final UserRequest request
	) {
		Integer userId = ((AuthUser) authentication.getPrincipal()).getUserId();
		return super.update(userId, request);
	}

	@DeleteMapping("/me")
	public ResponseEntity<?> deleteMe(@NonNull final Authentication authentication) {
		Integer userId = ((AuthUser) authentication.getPrincipal()).getUserId();
		return super.delete(userId);
	}

	@GetMapping("/me")
	public ResponseEntity<?> getMe(@NonNull final Authentication authentication) {
		Integer userId = ((AuthUser) authentication.getPrincipal()).getUserId();
		return super.getOne(userId);
	}

	// ADMIN-role's allowed endpoints

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PatchMapping("/{id}")
	@Override
	public ResponseEntity<?> update(
			@PathVariable("id") @NonNull final Integer id,
			@Validated(OnUpdate.class) @RequestBody @NonNull final UserRequest request
	) {
		return super.update(id, request);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> delete(@PathVariable("id") @NonNull final Integer id) {
		return super.delete(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> getOne(@PathVariable("id") @NonNull final Integer id) {
		return super.getOne(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	@Override
	public List<UserResponse> getAll(@NonNull final Pageable pageable) {
		return super.getAll(pageable);
	}
}
