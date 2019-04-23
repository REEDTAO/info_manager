package com.reed.info_manager.entity;

import lombok.Data;

@Data
public class UserRole {
    Integer userRoleId;
    Integer userId;
    Integer userGroupId;
    String userGroupName;
}
