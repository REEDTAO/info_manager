package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {
    int insertUserRole(UserRole userRole);
    UserRole selectUserRoleByUserRole(UserRole userRole);
}
