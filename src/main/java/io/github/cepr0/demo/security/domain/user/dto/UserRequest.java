package io.github.cepr0.demo.security.domain.user.dto;

import io.github.cepr0.crud.controller.OnCreate;
import io.github.cepr0.crud.controller.OnUpdate;
import io.github.cepr0.crud.model.CrudRequest;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRequest implements CrudRequest {
	@NotBlank(groups = OnCreate.class)
	@Size(min = 3, max = 32, groups = {OnCreate.class, OnUpdate.class})
	private String name;

	@NotBlank(groups = OnCreate.class)
	@Email(groups = {OnCreate.class, OnUpdate.class})
	private String email;

	@NotBlank(groups = OnCreate.class)
	@Size(min = 6, max = 16, groups = {OnCreate.class, OnUpdate.class})
	private String password;
}
