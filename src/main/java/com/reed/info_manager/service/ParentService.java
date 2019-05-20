package com.reed.info_manager.service;

import com.reed.info_manager.dao.ParentDao;
import com.reed.info_manager.entity.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {
    @Autowired
    ParentDao parentDao;
    public List<Parent> getParentByUserId(Integer id) {
        return  parentDao.getParentByUserId(id);
    }

    public int removeParentByUserId(Integer id, Integer userId) {
        return parentDao.removeParentByUserId(id,userId);
    }

    public Parent loginParent(Integer id, String password) {
        return parentDao.loginParent(id,password);
    }

    public int updateUserByUserId(Parent parent) {
        return parentDao.updateUserByUserId(parent);
    }

    public int removeStudentByUserId(Integer userId, Integer id) {
        return parentDao.removeStudentByUserId(userId,id);
    }

    public int bindStudent(Integer parentId, Integer userId) {
        return parentDao.bindStudent(parentId,userId);
    }
}
