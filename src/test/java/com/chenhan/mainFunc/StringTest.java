package com.chenhan.mainFunc;

public class StringTest {
    public static void main(String[] args) {

        test5();
    }

    public static void test1(){
        String s1 = "helloworld";
        String s2 = "helloworld";
        String s3 = "hello" + "world";
        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//true
        System.out.println(s2 == s3);//true
    }

    public static void test5(){
        String str1="abc";
        String str2="def";
        String str3=str1+str2;
        System.out.println("===========test5============");
        System.out.println(str3=="abcdef"); //false
        String str4 = "abcdef";
        System.out.println(str3 == str4);
    }
}
