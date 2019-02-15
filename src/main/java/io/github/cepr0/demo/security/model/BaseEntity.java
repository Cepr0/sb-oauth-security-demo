package io.github.cepr0.demo.security.model;

import io.github.cepr0.crud.model.IdentifiableEntity;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseEntity<ID extends Serializable> implements IdentifiableEntity<ID>, Persistable<ID> {

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{id=" + getId() + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BaseEntity that = (BaseEntity) o;
		return Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
}