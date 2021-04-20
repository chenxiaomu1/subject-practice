package com.chenhan.utils;

import java.lang.reflect.Method;

import com.chenhan.annotation.AllowAnnotation;
import com.chenhan.annotation.ChAnnotation;

public class GetAnnotations {
	
	
	public static Object getAnnotationObject(Class cls) {
		
		if(cls == null) return null;
		
		Object value = null;
		
		if(cls.isAnnotationPresent(AllowAnnotation.class)) {
			Method[] methods = cls.getDeclaredMethods();
			for(Method method : methods) {
				if(method.isAnnotationPresent(ChAnnotation.class)) {
					ChAnnotation anno = method.getAnnotation(ChAnnotation.class);
					//获取注解的值
					value = anno.name();
				}
			}
		}
		return value;
	}
	
}	
