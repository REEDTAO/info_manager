package com.reed.info_manager.dao;

import com.reed.info_manager.entity.Manager;
import com.reed.info_manager.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerDao {
    @Autowired
    ManagerMapper managerMapper;
    public Manager loginManager(Integer id, String password) {
        return managerMapper.loginMapper(id,password);
    }
}
