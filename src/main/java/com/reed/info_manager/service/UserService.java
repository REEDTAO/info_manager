package com.reed.info_manager.service;

import com.reed.info_manager.dao.UserDao;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.entity.UserInfoDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserDao userDao;

    public  String getUserNameByUserId(Integer taskCreatorId) {
        return userDao.getUserNameByUserId(taskCreatorId);
    }

    public User login(String id,String password) {
        return userDao.login(id,password);
    }


    public int addUsers(List<User> list) {
        return userDao.addUsers(list);
    }

    public UserInfoDetail getUserDetailByUserId(Integer userId) {
        return userDao.getUserDetailByUserId(userId);
    }

    public int updateUserByUserId(User user) {
        return userDao.updateUserByUserId(user);
    }

    public List<User> getUserByParentId(Integer id) {
        return userDao.getUserByParentId(id);
    }

    public int findUserByIdAndName(Integer id, String name) {
        return userDao.findUserByIdAndName(id,name);
    }


    public List<User> searchUserByName(String name) {
        return userDao.searchUserByName(name);
    }

    public List<User> searchUserById(Integer id) {
        return userDao.searchUserById(id);
    }

    public int deleteUserByUserId(Integer userId) {
        return userDao.deleteUserByUserId(userId);
    }
}
