package com.github.cepr0.demo.security.domain.resource;

import com.github.cepr0.crud.service.AbstractCrudService;
import com.github.cepr0.demo.security.domain.resource.dto.ResourceRequest;
import com.github.cepr0.demo.security.domain.resource.dto.ResourceResponse;
import com.github.cepr0.demo.security.model.Resource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends AbstractCrudService<Resource, Integer, ResourceRequest, ResourceResponse> implements ResourceService {
	public ResourceServiceImpl(@NonNull final ResourceRepo repo, @NonNull final ResourceMapper mapper) {
		super(repo, mapper);
	}
}
