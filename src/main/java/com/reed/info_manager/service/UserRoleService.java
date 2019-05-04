package com.reed.info_manager.service;


import com.reed.info_manager.dao.UserRoleDao;
import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleService {
    @Autowired
    UserRoleDao userRoleDao;
    public int joinUserGroup(UserRole userRole) {
        return userRoleDao.joinUserGroup(userRole);
    }

    public List<UserGroup> searchMyJoinUserGroup(Integer id) {
        return userRoleDao.searchMyJoinUserGroup(id);
    }

    public Integer deleteUserRoleByUserGroupId(Integer userId, Integer userGroupId) {
        return userRoleDao.deleteUserRoleByUserGroupId(userId,userGroupId);
    }
}
