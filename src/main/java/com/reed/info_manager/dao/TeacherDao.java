package com.reed.info_manager.dao;

import com.reed.info_manager.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDao {
    @Autowired
    TeacherMapper teacherMapper;
    public boolean login(String userId, String password) {
        return teacherMapper.selectTeacherByUserIdAndPassword(userId,password)!=null?true:false;
    }
}
