package com.github.cepr0.demo.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "resources")
@DynamicUpdate
@DynamicInsert
public class Resource extends IntIdEntity {

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User user;

	@Column(length = 64, nullable = false)
	private String name;

	@Type(type = "json")
	@Column(columnDefinition = "clob")
	private Map<String, Object> data;
}
