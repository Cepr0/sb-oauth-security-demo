package io.github.cepr0.demo.security.domain.resource.dto;

import io.github.cepr0.crud.model.CrudResponse;
import lombok.Data;

import java.util.Map;

@Data
public class ResourceResponse implements CrudResponse<Integer> {
	private Integer id;
	private String name;
	private Map<String, Object> data;
}
