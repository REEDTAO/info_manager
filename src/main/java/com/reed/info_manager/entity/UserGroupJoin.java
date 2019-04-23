package com.reed.info_manager.entity;


import lombok.Data;

@Data
public class UserGroupJoin extends UserGroup{
    String joinUrl;

    @Override
    public void setUserGroupId(String creatorId) {
        super.setUserGroupId(creatorId);
        joinUrl="<a href='./Join/" + userGroupId + "/" + name + "'>加入</a>";
    }

}
