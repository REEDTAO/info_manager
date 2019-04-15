package com.reed.info_manager.controller;

import com.reed.info_manager.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class StudentController {

    @Autowired
    StudentMapper studentMapper;

    @PostMapping("/insertStudent")
    public String insertStudent(@ModelAttribute Student student){
        return studentMapper.inssertStudent(student)+"";
    }


}
