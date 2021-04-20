package com.chenhan.controller;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chenhan.annotation.AllowAnnotation;
import com.chenhan.annotation.ChAnnotation;
import com.chenhan.service.RedisTestImpl;
import com.chenhan.threads.TestDistributedLockThread;
import com.chenhan.utils.GetAnnotations;

import lombok.extern.slf4j.Slf4j;

@RestController
//@Slf4j

public class TestController {

	@Autowired
	ApplicationContext context;

	@Autowired
	RedisTestImpl controller;


	@GetMapping("/test")
	public Object test(String lockname) throws ClassNotFoundException {

		Class<?> cl = Class.forName("com.chenhan.service.RedisTestImpl");

		//获取注解的值，并调用注解标注的方法
		Object value = GetAnnotations.getAnnotationObject(cl);

		Object result = controller.annotationtest(value);

		return result;


		//下面是测试分布式锁的，注释掉
		/*
		 * for(int i = 0;i < 10 ;i++) { TestDistributedLockThread th = new
		 * TestDistributedLockThread(controller); th.start(); }
		 */
	}

	@GetMapping("/beanTest")
	public void beanTest() {

		String id = context.getId();
		String displayName = context.getDisplayName();
		AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
		ApplicationContext parent = context.getParent();
		long startupDate = context.getStartupDate();
		Environment environment = context.getEnvironment();
		int beanDefinitionCount = context.getBeanDefinitionCount();

	}

}
