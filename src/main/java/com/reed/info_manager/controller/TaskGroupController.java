package com.reed.info_manager.controller;

import com.reed.info_manager.entity.User;
import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.service.UserGroupService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/task/group")
public class TaskGroupController {

    @Autowired
    HttpSession session;
    @Autowired
    UserGroupService userGroupService;

    final Logger logger = LoggerFactory.getLogger(TaskGroupController.class);


    @GetMapping("/myCreate")
    public String task(Model model){
        User user = (User)session.getAttribute("user");
        List<UserGroup> list = userGroupService.getAllGroup(user.getId());
        model.addAttribute("groupList",list);
        logger.debug(list.toString());
        System.out.println(list);
        return "taskGroup/taskGroupCreate";
    }

    @GetMapping("/add/{groupName}")
    public String addGroup(@PathVariable String groupName, Model model){
        User user = (User) session.getAttribute("user");

        if(userGroupService.addGroup(user.getId(),groupName)==1){
            model.addAttribute("message","添加成功！");
            return "redirect:/task/group/myCreate";
        }
        return "redirect:/login";

    }

    @GetMapping("/delete/{groupName}")
    public String deleteGroup(@PathVariable String groupName, Model model){
        User user = (User)session.getAttribute("user");
        if(userGroupService.deleteGroup(user.getId(),groupName)==1){
            model.addAttribute("message","删除成功！");
        }
        model.addAttribute("message","删除失败，请联系管理员修改！");
        return "redirect:/task/group/myCreate";
    }
}
