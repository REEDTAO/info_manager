package com.reed.info_manager.controller;


import com.reed.info_manager.entity.Parent;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.service.ParentService;
import com.reed.info_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller

public class UserInfoDetailController {

    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;
    @Autowired
    ParentService parentService;


    @GetMapping("/myInfoDetail")
    public String getMyInfoDetail(Model model){
        User user = (User) session.getAttribute("user");
        List<Parent> list = parentService.getParentByUserId(user.getId());
        model.addAttribute("list",list);
        return "userInfoDetail/index";
    }

    @GetMapping("/myInfoDetail/parent")
    public String getMyInfoDetailParent(Model model){
        Parent parent = (Parent) session.getAttribute("parent");
        List<User> list = userService.getUserByParentId(parent.getId());
        session.setAttribute("userList",list);
        return "parent/parentInfoDetail";
    }

    @PostMapping("/myInfoDetail/update")
    public String updateMyInfo(User user, Model model){

       if(userService.updateUserByUserId(user)==1){
           session.setAttribute("user",user);
           model.addAttribute("message","修改成功！");
       }else{
           model.addAttribute("message","修改失败，请联系管理员！");
       }
        return getMyInfoDetail(model);
    }
    @PostMapping("/myInfoDetail/parent/update")
    public String updateMyInfo(Parent parent, Model model){

        if(parentService.updateUserByUserId(parent)==1){
            session.setAttribute("user",parent);
            model.addAttribute("message","修改成功！");
        }else{
            model.addAttribute("message","修改失败，请联系管理员！");
        }
        return getMyInfoDetailParent(model);
    }


    @GetMapping("/myInfoDetail/unbindParent")
    public String unbindParent(Integer id,Model model){
        User user  = (User) session.getAttribute("user");
        if(parentService.removeParentByUserId(id,user.getId())==1){
            model.addAttribute("message","解除成功");
        }else{
            model.addAttribute("message","解除失败！请联系管理员！");
        }
        return  getMyInfoDetail(model);
    }

    @GetMapping("/myInfoDetail/parent/unbindStudent")
    public String unbindStudent(Integer id,Model model){
        Parent parent  = (Parent) session.getAttribute("parent");
        if(parentService.removeParentByUserId(parent.getId(),id)==1){
            model.addAttribute("message","解除成功");
        }else{
            model.addAttribute("message","解除失败！请联系管理员！");
        }
        return  getMyInfoDetailParent(model);
    }
    @PostMapping("/myInfoDetail/parent/bindStudent")
    public String bindStudent(Integer id,String name,Model model){
        Parent parent  = (Parent) session.getAttribute("user");
        if(userService.findUserByIdAndName(id,name)==1){
            if(parentService.bindStudent(parent.getId(),id)==1){
                model.addAttribute("message","绑定成功！");
            }else{
                model.addAttribute("message","绑定失败！");
            }
        }else{
            model.addAttribute("message","信息有误！");
        }
        return getMyInfoDetailParent(model);
    }

}
