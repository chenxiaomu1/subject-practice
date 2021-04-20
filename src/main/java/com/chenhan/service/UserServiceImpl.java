package com.chenhan.service;


import com.chenhan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    RedisTestImpl redisTestImpl;


    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public User logout(User user) {
        return null;
    }

    @Override
    public User getUserInfo(User user) {
        return null;
    }
}
