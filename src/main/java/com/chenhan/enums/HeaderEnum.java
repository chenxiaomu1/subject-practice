package com.chenhan.enums;

import org.apache.logging.log4j.core.util.Assert;

import java.util.Objects;

/**
 * 业务需求，header参数枚举
 */
public enum HeaderEnum {
	TOKEN("token"),
	CONTENT_TYPE("content_type");

	private String code;

	HeaderEnum(String code) {
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public static HeaderEnum getEnum(String code) throws Exception {
		Assert.requireNonEmpty(code, "code不能为空");
		for(HeaderEnum e : values()){
			if(Objects.equals(e.getCode(), code)){
				return e;
			}
		}
		return null;
	}

}
