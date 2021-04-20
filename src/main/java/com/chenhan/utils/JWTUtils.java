package com.chenhan.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.math.BigDecimal;
import java.util.Arrays;

public class JWTUtils {

    public static void main(String[] args) {
//        String str = "[{'area': '147', 'fpath': 'https:\\/\\/src.leju.com\\/imp\\/imp\\/encrypt\\/ymKRaJZnnGVxamyWmppmmJpmm5drlmXEcJ7FyMdhx2zFYcOXoG%2BWo6Ru_wm6540098557318845671.jpeg', 'price': '0', 'hx_info': 'H\\u6237\\u578b','house_jushi':'二居室'}, {'area': '141', 'fpath': 'https:\\/\\/src.leju.com\\/imp\\/imp\\/encrypt\\/m5aRm2RnmGVvnZzLyJKRl29plZealJHHm5nFmcNomGvIkpKXoG%2BWo6Ru_wm6540098557318845671.jpeg', 'price': '0', 'hx_info': 'D\\u6237\\u578b','house_jushi':'二居室'}, {'area': '150', 'fpath': 'https:\\/\\/src.leju.com\\/imp\\/imp\\/encrypt\\/lZGRnpZnbGVvmG%2Bay5holWhmmp6Xx2jFaWyYmcdgmWiVZpiXoG%2BWo6Ru_wm6540098557318845671.jpeg', 'price': '0', 'hx_info': 'C\\u6237\\u578b','house_jushi':'二居室'}, {'area': '141', 'fpath': 'https:\\/\\/src.leju.com\\/imp\\/imp\\/encrypt\\/l5WRbGRnnWVuZ2nGnJKSl5thlphsxGiZmmqTmpVpnGuWaZqXoG%2BWo6Ru_wm6540098557318845671.jpeg', 'price': '0', 'hx_info': 'B1\\u6237\\u578b','house_jushi':'二居室'}, {'area': '134', 'fpath': 'https:\\/\\/src.leju.com\\/imp\\/imp\\/encrypt\\/yZORa2FnmmVsb5yVyZFhyJlolshnmmaTbpyTxpNinG%2BVYJOXoG%2BWo6Ru_wm6540098557318845671.jpeg', 'price': '0', 'hx_info': '134\\u5e73\\u7c73\\u6237\\u578b','house_jushi':'二居室'}]";
//
//        JSONArray objects = JSON.parseArray(str);
//        Object[] objects1 = objects.toArray();
//        Arrays.stream(objects1).forEach(System.out::print);

        BigDecimal b1 = new BigDecimal("1.00012154");
        System.out.println(b1.doubleValue());

    }

}
