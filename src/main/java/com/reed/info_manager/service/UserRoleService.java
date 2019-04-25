package com.reed.info_manager.service;


import com.reed.info_manager.dao.UserRoleDao;
import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    UserRoleDao userRoleDao;
    public int joinUserGroup(UserRole userRole) {
        return userRoleDao.joinUserGroup(userRole);
    }

    public List<UserGroup> searchMyJoinUserGroup(Integer id) {
        return userRoleDao.searchMyJoinUserGroup(id);
    }
}
