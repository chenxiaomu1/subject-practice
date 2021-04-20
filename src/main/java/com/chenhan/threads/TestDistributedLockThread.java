package com.chenhan.threads;

import org.springframework.beans.factory.annotation.Autowired;

import com.chenhan.controller.TestController;
import com.chenhan.distributelock.RedisDistributedLock;
import com.chenhan.service.RedisTestImpl;

public class TestDistributedLockThread extends Thread{
	
	
	@Autowired
	RedisTestImpl controller;
	
	 public TestDistributedLockThread(RedisTestImpl controller) {
		 this.controller = controller;
	 }
	 
	 @Override
	public void run() {
		 try {
			controller.testDistributedLock("test");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
