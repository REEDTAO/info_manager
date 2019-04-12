package com.reed.info_manager.controller;



import com.reed.info_manager.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    StudentMapper studentMapper;

    @GetMapping("/hello")
    public String sayHello(){
        return studentMapper.selectAllStudent().toString();
    }

}
