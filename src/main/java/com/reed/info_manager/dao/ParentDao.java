package com.reed.info_manager.dao;

import com.reed.info_manager.entity.Parent;
import com.reed.info_manager.mapper.ParentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParentDao {
    @Autowired
    ParentMapper parentMapper;
    public List<Parent> getParentByUserId(Integer id) {
        return parentMapper.getParentByUserId(id);
    }

    public int removeParentByUserId(Integer id, Integer userId) {
        return parentMapper.removeParentByUserId(id,userId);
    }

    public Parent loginParent(Integer id, String password) {
        return parentMapper.loginParent(id,password);
    }

    public int updateUserByUserId(Parent parent) {
        return parentMapper.updateUserByUserId(parent);
    }

    public int removeStudentByUserId(Integer userId, Integer id) {
        return parentMapper.removeStudentByUserId(userId,id);
    }

    public int bindStudent(Integer parentId, Integer userId) {
        return parentMapper.bindStudent(parentId,userId);
    }
}
