package io.github.cepr0.demo.security.domain.user.dto;

import io.github.cepr0.crud.model.CrudResponse;
import lombok.Data;

@Data
public class UserResponse implements CrudResponse<Integer> {
	private Integer id;
	private String name;
	private String email;
}
