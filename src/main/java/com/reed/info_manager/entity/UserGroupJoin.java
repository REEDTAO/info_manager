package com.reed.info_manager.entity;


import lombok.Data;

@Data
public class UserGroupJoin extends UserGroup{
    String joinUrl;

    @Override
    public void setName(String name) {
        super.setName(name);
        joinUrl="<button class='btn btn-default' onclick='joinGroup(this)' url='./Join/" + userGroupId + "/" + name + "'>加入</button>";
    }

}
