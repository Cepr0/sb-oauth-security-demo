package io.github.cepr0.demo.security.domain.resource.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cepr0.crud.controller.OnCreate;
import io.github.cepr0.crud.controller.OnUpdate;
import io.github.cepr0.crud.model.CrudRequest;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
public class ResourceRequest implements CrudRequest {

	@Null(groups = {OnCreate.class, OnUpdate.class})
	@JsonProperty("userId") private Integer user;

	@NotBlank(groups = OnCreate.class)
	@Size(min = 3, max = 32)
	private String name;

	@NotNull(groups = OnCreate.class)
	private Map<String, Object> data;
}
