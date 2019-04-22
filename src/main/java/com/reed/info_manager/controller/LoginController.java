package com.reed.info_manager.controller;




import com.reed.info_manager.entity.User;
import com.reed.info_manager.service.UserService;
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
    HttpSession session;

    @Autowired
    UserService userService;


    @GetMapping
    public String login(){
        return "loginPage";
    }

    @PostMapping("/user")
    public String postLogin(String id,String password){
        System.out.println(id+" "+password);
        User user = userService.login(id,password);

        if(user==null) return "redirect:/login";
        session.setAttribute("user",user);
        return "redirect:/index";
    }



}
