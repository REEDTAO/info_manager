package com.reed.info_manager.controller;

import com.reed.info_manager.entity.Task;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    HttpSession session;
    @Autowired
    TaskService taskService;
    @GetMapping()
    public String getIndex(Model model){
        User user = (User) session.getAttribute("user");
        List<Task> list = taskService.getMyReceiveTaskListUnfinishedOrderByTime(user.getId());
        model.addAttribute("list",list);
        return "indexPage";
    }

    @GetMapping("/parent")
    public String getParentIndex(){
        return "parent/index";
    }
}
