package com.reed.info_manager;

import com.reed.info_manager.entity.Student;
import com.reed.info_manager.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InfoManagerApplicationTests {

    @Autowired
    StudentMapper studentMapper;

    @Test
    public void insertStudent(){
        List<Student> studentList = new ArrayList<>();
        for(int i=0;i<10;i++){
            Student student = new Student();
            student.setClassName("计科1151");
            student.setStudentId("20151161120"+i);
            student.setName("陈"+i);
            student.setSex("男");
            student.setTel("120"+i);
            student.setPassword("5252");
            student.setAddr("湛江");
            student.setEmail("163.com");
            studentList.add(student);
        }
        studentMapper.inssertStudentList(studentList);

    }

}
