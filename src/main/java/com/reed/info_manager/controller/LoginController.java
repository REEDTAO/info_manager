package com.reed.info_manager.controller;





import com.reed.info_manager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    TeacherService teacherService;


    @GetMapping
    public String login(){
        return "loginPage";
    }

    @PostMapping("/teacher")
    public String loginTeacher(String userId, String password, HttpSession session){
        if(teacherService.login(userId,password)){
            session.setAttribute("userId",userId);
            session.setAttribute("userType","teacher");
            return "index";
        }else{
            return "login";
        }
    }

}
