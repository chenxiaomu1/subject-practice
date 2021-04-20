package com.chenhan.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.chenhan.enums.ChEnum;


@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface ChAnnotation {
	//String类型，必须传值，name()代表属性值而不是方法
    String name();

    //int类型，带默认值可以不传值
    int age() default 0;

    //boolean类型
    boolean bool() default false;

    //char 类型
    char cha() default ' ';

    //各种数组类型
    String[] strs() default {};

    //枚举类型
    ChEnum myEnum() default ChEnum.A;
}
