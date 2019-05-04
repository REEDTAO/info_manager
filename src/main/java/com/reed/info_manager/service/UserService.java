package com.reed.info_manager.service;

import com.reed.info_manager.dao.UserDao;
import com.reed.info_manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserDao userDao;
    public User login(String id,String password) {
        return userDao.login(id,password);
    }


    public int addUsers(List<User> list) {
        return userDao.addUsers(list);
    }
}
