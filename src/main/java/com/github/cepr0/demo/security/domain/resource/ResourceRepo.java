package com.github.cepr0.demo.security.domain.resource;

import com.github.cepr0.crud.repo.CrudRepo;
import com.github.cepr0.demo.security.model.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ResourceRepo extends CrudRepo<Resource, Integer> {
	@Query("select r from Resource r where (r.user.id = ?#{userId()} or ?#{hasRole('ROLE_ADMIN')} = true)")
	@NonNull
	@Override
	List<Resource> getAll(@NonNull Pageable pageable);

	@Query("select r from Resource r where r.id = ?1 and (r.user.id = ?#{userId()} or ?#{hasRole('ROLE_ADMIN')} = true)")
	@Override
	Optional<Resource> getById(Integer integer);

	@Query("select r from Resource r where r.id = ?1 and (r.user.id = ?#{userId()} or ?#{hasRole('ROLE_ADMIN')} = true)")
	@Override
	Optional<Resource> getToUpdateById(Integer integer);

	@Modifying
	@Query("delete from Resource r where r.id = ?1 and (r.user.id = ?#{userId()} or ?#{hasRole('ROLE_ADMIN')} = true)")
	@Override
	int delById(Integer integer);
}
