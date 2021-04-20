package com.chenhan.functioninterface;

import com.alibaba.fastjson.JSONObject;
import com.chenhan.entity.Person;
import com.chenhan.entity.User;

import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.Function;

/*
* 函数式接口测试
* */
public class TestFunction {

    /**
     *  BiConsumer 对两个对象进行操作，无返回值，但是可变更对象内容
     */
    public static void valueTransfer(Person p1, User p2){
        BiConsumer<Person, User> biConsumer = (o1,o2) -> {
            o2.setUsername(o1.getName());
            System.out.println(o1.getName() + " " + o2.getPassword());
        };
        biConsumer.accept(p1, p2);
    }


    /**
     * Function
     * @param
     */
    public static User functionTest(Person p){
        Function<Person, User> fuc = a -> {
            User user = new User();
            user.setUsername(a.getName());
            return user;
        };
        return fuc.apply(p);
    }

    public static void main(String[] args) {
       /*User u1 = new User();
       u1.setPassword("1234");
       Person person = new Person("chenhan", 1600, 26, "male", "shanghai");

       valueTransfer(person,u1);
       System.out.println(u1.getUsername());

        User user = functionTest(person);
        System.out.println(user.getUsername());*/


        StringJoiner stringJoiner = new StringJoiner(",", "chen","han");
        stringJoiner.setEmptyValue("empty");
        stringJoiner.add("dailyWork");
        System.out.println(stringJoiner.toString());

    }
}
