package com.reed.info_manager.controller;

import com.reed.info_manager.entity.Parent;
import com.reed.info_manager.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    ParentService parentService;
    @GetMapping
    public String getRegister(){
        return "registerPage";
    }
    @PostMapping
    public String postRegister(Parent parent, Model model){

        int num = 0;
        try{
            num=parentService.registerParent(parent);
        }catch (Exception e){
            model.addAttribute("message","编号不可用！");
            model.addAttribute("parent",parent);
            return "registerPage";
        }
        if(num==1){
            model.addAttribute("message","注册成功！");
            return "loginPage";
        }else{
            model.addAttribute("message","注册失败！");
            return "registerPage";
        }
    }
}
