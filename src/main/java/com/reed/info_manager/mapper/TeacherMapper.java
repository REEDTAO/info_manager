package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface TeacherMapper {
    Teacher selectTeacherByUserIdAndPassword(@Param("teacherId") String userId, @Param("password") String password);
}
