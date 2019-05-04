package com.reed.info_manager.entity;

import lombok.Data;

@Data
public class UserGroup {
    Integer userGroupId;
    String name;
    Integer creatorId;
    String creatorName;


}
