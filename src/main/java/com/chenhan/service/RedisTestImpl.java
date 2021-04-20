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
		
		System.out.println(Thread.currentThread().getName() + "��ȡ����ҵ�����С�����������");
		Thread.sleep(3000);
		
		redisDistributedLock.releaseLock(lockname, identifier);
		System.out.println(Thread.currentThread().getName() + "ҵ������ɣ��ͷ���������������");
		
	}
	
	@ChAnnotation(name = "chenhan")
	public Object annotationtest(Object value) {
		System.out.println(value);
		return value;
	}
}
