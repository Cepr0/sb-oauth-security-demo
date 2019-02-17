package com.github.cepr0.demo.security.domain.user.dto;

import com.github.cepr0.crud.dto.CrudResponse;
import lombok.Data;

@Data
public class UserResponse implements CrudResponse<Integer> {
	private Integer id;
	private String name;
	private String email;
}
