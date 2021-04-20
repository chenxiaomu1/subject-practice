package com.chenhan.config;

import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class RedisConfiguration {
	
	
	
	//更改redis的默认序列化器，把数据以json的形式存入redis
//	@Bean
////	@Lazy
////	@Scope("prototype")
//	@ConditionalOnMissingBean(name = "redisTemplate")
//	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
//			throws UnknownHostException {
//		RedisTemplate<Object, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(redisConnectionFactory);
//		Jackson2JsonRedisSerializer<Object> ser = new Jackson2JsonRedisSerializer<Object>(Object.class);
//		template.setDefaultSerializer(ser);
//		return template;
//	}
}
