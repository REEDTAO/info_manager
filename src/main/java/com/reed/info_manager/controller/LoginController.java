package com.reed.info_manager.controller;




import com.reed.info_manager.entity.Manager;
import com.reed.info_manager.entity.Parent;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.service.ManagerService;
import com.reed.info_manager.service.ParentService;
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

    @Autowired
    ParentService parentService;
    @Autowired
    ManagerService managerService;


    @GetMapping
    public String login(){
        return "loginPage";
    }

    @PostMapping("/user")
    public String postLogin(String id,String password){
        User user = userService.login(id,password);

        if(user==null) return "redirect:/login";
        session.setAttribute("user",user);
        return "redirect:/index";
    }
    @PostMapping("/parent")
    public String parentLogin(Integer id,String password){
        Parent parent = parentService.loginParent(id,password);
        session.setAttribute("parent",parent);
        return "redirect:/myInfoDetail/parent";
    }

    @PostMapping("/manager")
    public String managerLogin(Integer id,String password){
        Manager manager = managerService.loginManager(id,password);
        session.setAttribute("manager",manager);
        return "redirect:/manager/index";
    }

    @GetMapping("/logout")
    public String logOut(){
        session.removeAttribute("user");
        session.removeAttribute("parent");
        session.removeAttribute("manager");
        return "redirect:/login";
    }



}
