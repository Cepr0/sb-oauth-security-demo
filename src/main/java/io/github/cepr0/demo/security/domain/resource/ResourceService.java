package io.github.cepr0.demo.security.domain.resource;

import io.github.cepr0.crud.service.CrudService;
import io.github.cepr0.demo.security.domain.resource.dto.ResourceRequest;
import io.github.cepr0.demo.security.domain.resource.dto.ResourceResponse;
import io.github.cepr0.demo.security.model.Resource;

public interface ResourceService extends CrudService<Resource, Integer, ResourceRequest, ResourceResponse> {
}
