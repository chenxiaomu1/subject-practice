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
		tokenCache = CacheBuilder.newBuilder().expireAfterWrite(expireTime,TimeUnit.SECONDS).build();//是在指定项在一定时间内没有创建/覆盖时，会移除该key，下次取的时候从loading中取
	}
	
	/**
	 * 将token当做key，并存储当前时间，每次用户调用接口，刷新一次token时间
	 */
	public void createToken(String token) {
		tokenCache.put(token, MyDateUtils.formatDateTime(new Date()));
	}
	
	public void refreshToken(String token) {
		createToken(token);//同一个用户token不变，刷新时间
	}
	
	/**
	 * 验证token
	 * @throws Exception 
	 */
	public void validateToken(String token) throws Exception {
		if(StringUtils.isBlank(token)) throw new Exception("token不能为空");
		if(tokenCache.getIfPresent(token) == null) throw new Exception("token失效");
	}
	
	
	
}
