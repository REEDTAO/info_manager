package com.reed.info_manager.dao;

import com.reed.info_manager.entity.User;
import com.reed.info_manager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    UserMapper userMapper;
    public User login(String id,String password) {
        return userMapper.selectUserByIdAndPassword(id,password);
    }

    public int addUsers(List<User> list) {
        return userMapper.addUsers(list);
    }

    public String getUserNameByUserId(Integer taskCreatorId) {
        return  userMapper.getUserNameByUserId(taskCreatorId);
    }
}
