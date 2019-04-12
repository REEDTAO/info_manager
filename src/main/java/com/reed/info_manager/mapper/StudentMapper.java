package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.util.List;



@Mapper
public interface StudentMapper {
    @Select("select * from student;")
    List<Student> selectAllStudent();
}
