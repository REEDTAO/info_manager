package com.reed.info_manager.service;



import com.reed.info_manager.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    TeacherDao teacherdao;

    public boolean login(String userId, String password) {
        return teacherdao.login(userId,password);
    }
}
