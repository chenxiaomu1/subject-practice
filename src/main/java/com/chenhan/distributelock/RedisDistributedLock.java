package com.chenhan.distributelock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 实现思想：
		（1）获取锁的时候，使用setnx加锁，并使用expire命令为锁添加一个超时时间，超过该时间则自动释放锁，锁的value值为一个随机生成的UUID，
			通过此在释放锁的时候进行判断。
		（2）获取锁的时候还设置一个获取的超时时间，若超过这个时间则放弃获取锁。
		（3）释放锁的时候，通过UUID判断是不是该锁，若是该锁，则执行delete进行锁释放。
 */
@Component
public class RedisDistributedLock {
	
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	//锁超时时间，超过此时间自动释放锁，防止异常导致锁不释放
	
	@Value("${lockExpireTime}")
	private long lockExpireTime;
	
	//获取锁超时时间，超过此时间放弃获取锁
	@Value("${acquireExpire}")
	private long acquireExpire;
	
	public String lockWithTimeOut(String lockname){
		String releaseIdentifier = null;
		
		//锁名，缓存key值，支持多个业务锁
		String key = "lock:" + lockname;
		//锁value值
		String identifier = UUID.randomUUID().toString();
		
		// 获取锁的超时时间，超过这个时间则放弃获取锁
		long end = System.currentTimeMillis() + acquireExpire;
		
		while(System.currentTimeMillis() < end) {
			boolean setnx = redisTemplate.opsForValue().setIfAbsent(key, identifier, lockExpireTime, TimeUnit.MILLISECONDS);
			if(setnx) {
				releaseIdentifier = identifier;
				return releaseIdentifier;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		return releaseIdentifier;
	}
	
	public boolean releaseLock(String lockname,String identifier) {
		boolean release = false;
		String key = "lock:" + lockname;
		//需要解锁的锁存在
		if(identifier.equals(redisTemplate.opsForValue().get(key))) {
			redisTemplate.delete(key);
			release = true;
		}
		
		return release;
	}
	
	
}
