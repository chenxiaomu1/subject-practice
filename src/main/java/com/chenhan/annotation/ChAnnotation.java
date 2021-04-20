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
	//String���ͣ����봫ֵ��name()��������ֵ�����Ƿ���
    String name();

    //int���ͣ���Ĭ��ֵ���Բ���ֵ
    int age() default 0;

    //boolean����
    boolean bool() default false;

    //char ����
    char cha() default ' ';

    //������������
    String[] strs() default {};

    //ö������
    ChEnum myEnum() default ChEnum.A;
}
