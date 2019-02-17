package com.github.cepr0.demo.security.domain.resource;

import com.github.cepr0.crud.service.CrudService;
import com.github.cepr0.demo.security.domain.resource.dto.ResourceRequest;
import com.github.cepr0.demo.security.domain.resource.dto.ResourceResponse;
import com.github.cepr0.demo.security.model.Resource;

public interface ResourceService extends CrudService<Resource, Integer, ResourceRequest, ResourceResponse> {
}
