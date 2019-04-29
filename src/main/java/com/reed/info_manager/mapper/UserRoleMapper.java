package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    int insertUserRole(UserRole userRole);
    UserRole selectUserRoleByUserRole(UserRole userRole);

    List<UserGroup> selectMyJoinUserGroup(Integer id);

    Integer deleteUserRoleByUserGroupId(@Param("userId") Integer userId,@Param("userGroupId") Integer userGroupId);
}
