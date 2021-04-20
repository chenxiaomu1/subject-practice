package com.chenhan.distributelock;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * ʵ��˼�룺
		��1����ȡ����ʱ��ʹ��setnx��������ʹ��expire����Ϊ�����һ����ʱʱ�䣬������ʱ�����Զ��ͷ���������valueֵΪһ��������ɵ�UUID��
			ͨ�������ͷ�����ʱ������жϡ�
		��2����ȡ����ʱ������һ����ȡ�ĳ�ʱʱ�䣬���������ʱ���������ȡ����
		��3���ͷ�����ʱ��ͨ��UUID�ж��ǲ��Ǹ��������Ǹ�������ִ��delete�������ͷš�
 */
@Component
public class RedisDistributedLock {
	
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	//����ʱʱ�䣬������ʱ���Զ��ͷ�������ֹ�쳣���������ͷ�
	
	@Value("${lockExpireTime}")
	private long lockExpireTime;
	
	//��ȡ����ʱʱ�䣬������ʱ�������ȡ��
	@Value("${acquireExpire}")
	private long acquireExpire;
	
	public String lockWithTimeOut(String lockname){
		String releaseIdentifier = null;
		
		//����������keyֵ��֧�ֶ��ҵ����
		String key = "lock:" + lockname;
		//��valueֵ
		String identifier = UUID.randomUUID().toString();
		
		// ��ȡ���ĳ�ʱʱ�䣬�������ʱ���������ȡ��
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
		//��Ҫ������������
		if(identifier.equals(redisTemplate.opsForValue().get(key))) {
			redisTemplate.delete(key);
			release = true;
		}
		
		return release;
	}
	
	
}
