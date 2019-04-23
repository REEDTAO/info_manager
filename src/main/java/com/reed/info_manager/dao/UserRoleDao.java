package com.reed.info_manager.dao;


import com.reed.info_manager.entity.UserRole;
import com.reed.info_manager.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.reed.info_manager.constant.Constant.FAIL_JOIN_USER_ROLE;

@Repository
public class UserRoleDao {
    @Autowired
    UserRoleMapper userRoleMapper;

    public int joinUserGroup(UserRole userRole) {
        if (userRoleMapper.selectUserRoleByUserRole(userRole)==null)
        return userRoleMapper.insertUserRole(userRole);
        else return FAIL_JOIN_USER_ROLE;
    }
}
