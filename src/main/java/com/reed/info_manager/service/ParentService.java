package com.reed.info_manager.service;

import com.reed.info_manager.dao.ParentDao;
import com.reed.info_manager.entity.Parent;
import com.reed.info_manager.entity.User;
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

    public List<Parent> searchUserByName(String name) {
        return parentDao.searchUserByName(name);
    }

    public List<Parent> searchParentById(Integer id) {
        return parentDao.searchParentById(id);
    }

    public Integer updateParentByUserId(Parent parent) {
        return parentDao.updateParentByUserId(parent);
    }

    public Integer deleteParentByUserId(Integer parentId) {
        return parentDao.deleteParentByUserId(parentId);
    }

    public Integer addParents(List<User> list) {
        return parentDao.addParents(list);
    }

    public int registerParent(Parent parent) {
        return parentDao.registerParent(parent);
    }
}
