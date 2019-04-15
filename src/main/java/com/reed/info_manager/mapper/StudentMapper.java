package com.reed.info_manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> selectAllStudent();


    int inssertStudent(Student student);
}
