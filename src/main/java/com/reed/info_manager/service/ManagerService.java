package com.reed.info_manager.service;

import com.reed.info_manager.dao.ManagerDao;
import com.reed.info_manager.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    ManagerDao managerDao;
    public Manager loginManager(Integer id, String password) {
        return managerDao.loginManager(id,password);
    }
}
