package com.chenhan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = true, length = 20)
	private Long id;
	
	@Column(name = "username", nullable = true, length = 20)
	private String username;
	
	@Column(name = "password", length = 20)
	private String password;
	
}
