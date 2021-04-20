package com.chenhan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenhan.annotation.AllowAnnotation;
import com.chenhan.annotation.ChAnnotation;
import com.chenhan.distributelock.RedisDistributedLock;

@Service
@AllowAnnotation
public class RedisTestImpl {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	RedisDistributedLock redisDistributedLock;
	
	public void testDistributedLock(String lockname) throws InterruptedException {
		String identifier = redisDistributedLock.lockWithTimeOut(lockname);
		
		System.out.println(Thread.currentThread().getName() + "获取锁，业务处理中······");
		Thread.sleep(3000);
		
		redisDistributedLock.releaseLock(lockname, identifier);
		System.out.println(Thread.currentThread().getName() + "业务处理完成，释放锁······");
		
	}
	
	@ChAnnotation(name = "chenhan")
	public Object annotationtest(Object value) {
		System.out.println(value);
		return value;
	}
}
