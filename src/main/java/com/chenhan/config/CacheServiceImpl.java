package com.chenhan.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chenhan.utils.MyDateUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import lombok.Getter;

@Service
public class CacheServiceImpl {
	
	private static Cache<String,String> tokenCache;
	
	@Value("${token.expireTime}")
	@Getter
	private long expireTime;
	
	@PostConstruct
	private void doInit() {
		tokenCache = CacheBuilder.newBuilder().expireAfterWrite(expireTime,TimeUnit.SECONDS).build();//����ָ������һ��ʱ����û�д���/����ʱ�����Ƴ���key���´�ȡ��ʱ���loading��ȡ
	}
	
	/**
	 * ��token����key�����洢��ǰʱ�䣬ÿ���û����ýӿڣ�ˢ��һ��tokenʱ��
	 */
	public void createToken(String token) {
		tokenCache.put(token, MyDateUtils.formatDateTime(new Date()));
	}
	
	public void refreshToken(String token) {
		createToken(token);//ͬһ���û�token���䣬ˢ��ʱ��
	}
	
	/**
	 * ��֤token
	 * @throws Exception 
	 */
	public void validateToken(String token) throws Exception {
		if(StringUtils.isBlank(token)) throw new Exception("token����Ϊ��");
		if(tokenCache.getIfPresent(token) == null) throw new Exception("tokenʧЧ");
	}
	
	
	
}
