package com.reed.info_manager.controller;

import com.reed.info_manager.entity.Task;
import com.reed.info_manager.entity.TaskReply;
import com.reed.info_manager.service.TaskReplyService;
import com.reed.info_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/task/detail")
public class TaskDetailController {
    @Autowired
    TaskService taskService;
    @Autowired
    TaskReplyService taskReplyService;
    @GetMapping("/unfinished/{taskId}")
    public String getUnifinishedDetail(@PathVariable("taskId")Integer taskId, Model model){
        Task task = taskService.getTaskByTaskId(taskId);
        model.addAttribute("task",task);
        List<TaskReply> list = taskReplyService.getTaskReplyListByTaskId(taskId);
        model.addAttribute("taskReplyList",list);
        return "task/taskUnfinishedDetail";
    }
    @GetMapping("/finished/{taskId}")
    public String getFinishedDetail(@PathVariable("taskId")Integer taskId, Model model){
        return getUnifinishedDetail(taskId,model);
    }
}
