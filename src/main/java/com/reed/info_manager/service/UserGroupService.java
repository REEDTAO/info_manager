package com.reed.info_manager.service;

import com.reed.info_manager.dao.UserGroupDao;
import com.reed.info_manager.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupService {
    @Autowired
    UserGroupDao userGroupDao;
    public int addGroup(Integer id, String groupName) {
        return userGroupDao.addGroup(id,groupName);
    }

    public List<UserGroup> getAllGroup(Integer id) {
        return userGroupDao.getAllGroup(id);
    }

    public int deleteGroup(Integer id, String groupName) {
        return userGroupDao.deleteGroup(id,groupName);
    }
}
