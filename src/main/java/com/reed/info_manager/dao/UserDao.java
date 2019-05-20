package com.reed.info_manager.dao;

import com.reed.info_manager.entity.User;
import com.reed.info_manager.entity.UserInfoDetail;
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

    public UserInfoDetail getUserDetailByUserId(Integer userId) {
        return userMapper.getUserDetailByUserId(userId);
    }

    public int updateUserByUserId(User user) {
        return userMapper.updateUserByUserId(user);
    }

    public List<User> getUserByParentId(Integer id) {
        return userMapper.getUserByParentId(id);
    }

    public int findUserByIdAndName(Integer id, String name) {
        return userMapper.findUserByIdAndName(id,name);
    }
}
