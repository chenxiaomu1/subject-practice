package com.chenhan.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.chenhan.entity.User;

public interface UserRpt extends JpaRepository<User, Long> , JpaSpecificationExecutor<User>{

}
