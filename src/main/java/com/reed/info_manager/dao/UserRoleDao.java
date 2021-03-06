package com.reed.info_manager.dao;


import com.reed.info_manager.entity.User;
import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.entity.UserRole;
import com.reed.info_manager.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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



    public List<UserGroup> searchMyJoinUserGroup(Integer id) {
        return userRoleMapper.selectMyJoinUserGroup(id);
    }

    public Integer deleteUserRoleByUserGroupId(Integer userId, Integer userGroupId) {
        return userRoleMapper.deleteUserRoleByUserGroupId(userId,userGroupId);
    }

    public List<User> getUserByUserGroupId(Integer userGroupId) {

        return userRoleMapper.getUserByUserGroupId(userGroupId);
    }

    public int deleteUserRoleByUserGroupIdAndUserId(Integer userGroupId, Integer userId) {
        return  userRoleMapper.deleteUserRoleByUserGroupIdAndUserId(userGroupId,userId);
    }
}
