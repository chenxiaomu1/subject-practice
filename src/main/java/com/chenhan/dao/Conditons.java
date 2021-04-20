package com.chenhan.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


public class Conditons<T> implements Specification<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String key;
	private String value;
	
	public Conditons(String key,String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		Predicate predicate = criteriaBuilder.equal(root.get(key), value);
		
		return predicate;
	}

}
