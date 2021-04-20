package com.chenhan.utils;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

public class MyDateUtils {
	
	public static void main(String[] args) {
		String formatDateTime = MyDateUtils.formatDateTime(new Date());
		System.out.println(formatDateTime);
	}
	//将时间转换为指定格式的字符串
	public static String formatDateTime(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
	}
}
