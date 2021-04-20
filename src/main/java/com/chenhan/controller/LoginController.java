/*
package com.chenhan.controller;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chenhan.dao.UserRpt;
import com.chenhan.entity.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/practice")
@Slf4j
public class LoginController {
	
	@Autowired
	private UserRpt userRpt;
	
//	private static Logger log = (Logger) LoggerFactory.getLogger(LoginController.class);
	
	@Value(value="${defineValue}")
	private String profile;

	@PostMapping("/login")
	public User login(@RequestBody User user) {
		
		log.debug(profile);
		
		log.trace("LoginController======trace");  
        log.debug("LoginController======debug");  
        log.info("LoginController======info");  
        log.warn("LoginController======warn");  
        log.error("LoginController======error");  
		
//		Optional<User> findOne = userRpt.findOne(new Specification<User>() {
//			
//			@Override
//			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
//				Path<Object> exp = root.get("username");
//				return builder.and(builder.equal(exp,user.getUsername()));
//			}
//		});

		Optional<User> findOne = userRpt.findOne((root,query,builder) -> {
			Path<Object> exp = root.get("username");
			Path<Object> exp1 = root.get("password");
			return builder.and(builder.and(builder.equal(exp,user.getUsername()),builder.equal(exp1,user.getPassword())));
		});
		
		return findOne.isPresent()?findOne.get():null;//如果findone没有值get()方法会返回异常NoSuchElementException
		
	}
}
*/
