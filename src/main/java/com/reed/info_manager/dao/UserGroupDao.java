package com.reed.info_manager.dao;

import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.entity.UserGroupJoin;
import com.reed.info_manager.mapper.UserGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserGroupDao {
    @Autowired
    UserGroupMapper userGroupMapper;
    public int addGroup(Integer id, String groupName,String creatorName) {
        return userGroupMapper.addGroup(id, groupName,creatorName);
    }

    public List<UserGroup> getAllGroup(Integer id) {
        return userGroupMapper.selectAllGroupById(id);
    }

    public int deleteGroup(Integer id, String groupName) {
        return userGroupMapper.deleteGroup(id,groupName);
    }

    public List<UserGroupJoin> searchGroupByNameForPage(String name) {
        return userGroupMapper.selectAllGroupByGroupName(name);
    }

}
