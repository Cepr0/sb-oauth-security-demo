package com.github.cepr0.demo.security.model;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Getter
@Setter
@MappedSuperclass
@TypeDef(name = "json", typeClass = JsonStringType.class)
public abstract class IntIdEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue
	private Integer id;

	@Version
	private Integer version;

	@Override
	public boolean isNew() {
		return id == null;
	}
}
