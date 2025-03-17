package com.ku.service;

import com.ku.entity.User;
import com.ku.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

//    public User getUserById(Long id) {
//        return userMapper.findById(id);
//    }
//
//    public List<User> getAllUsers() {
//        return userMapper.findAll();
//    }
//
//    public void createUser(User user) {
//        userMapper.insert(user);
//    }
//
//    public void updateUser(User user) {
//        userMapper.update(user);
//    }
//
//    public void deleteUser(Long id) {
//        userMapper.delete(id);
//    }
}

