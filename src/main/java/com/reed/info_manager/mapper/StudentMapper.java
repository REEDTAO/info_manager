package com.reed.info_manager.mapper;

import com.reed.info_manager.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> selectAllStudent();


    int inssertStudentList(List<Student> student);
}
