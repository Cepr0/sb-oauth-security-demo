package com.github.cepr0.demo.security.api;

import com.github.cepr0.crud.controller.AbstractCrudController;
import com.github.cepr0.crud.controller.OnCreate;
import com.github.cepr0.crud.controller.OnUpdate;
import com.github.cepr0.demo.security.domain.resource.ResourceService;
import com.github.cepr0.demo.security.domain.resource.dto.ResourceRequest;
import com.github.cepr0.demo.security.domain.resource.dto.ResourceResponse;
import com.github.cepr0.demo.security.domain.user.dto.AuthUser;
import com.github.cepr0.demo.security.model.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("resources")
@RestController
public class ResourceController extends AbstractCrudController<Resource, Integer, ResourceRequest, ResourceResponse> {

	public ResourceController(@NonNull final ResourceService service) {
		super(service);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResourceResponse create(
			@Validated(OnCreate.class) @RequestBody @NonNull final ResourceRequest request,
			@NonNull final Authentication authentication
			) {
		Integer userId = ((AuthUser) authentication.getPrincipal()).getUserId();
		request.setUser(userId);
		return super.create(request);
	}

	@PatchMapping("/{id}")
	@Override
	public ResponseEntity<?> update(
			@PathVariable("id") @NonNull final Integer id,
			@Validated(OnUpdate.class) @RequestBody @NonNull final ResourceRequest request
	) {
		return super.update(id, request);
	}

	@DeleteMapping("/{id}")
	@Override
	public ResponseEntity<?> delete(@PathVariable("id") @NonNull final Integer integer) {
		return super.delete(integer);
	}

	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> getOne(@PathVariable("id") @NonNull final Integer integer) {
		return super.getOne(integer);
	}

	@GetMapping
	@Override
	public List<ResourceResponse> getAll(@NonNull final Pageable pageable) {
		return super.getAll(pageable);
	}
}
