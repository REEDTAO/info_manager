
package com.reed.info_manager.controller;

import com.reed.info_manager.entity.Task;
import com.reed.info_manager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager/taskInfoManage")
public class ManagerTaskController {
    @Autowired
    TaskService taskService;
    @GetMapping
    public String getTaskInfoManage(){
        return "manager/taskInfoManage";
    }

    @GetMapping("/searchTitle/{title}")
    @ResponseBody
    public Map searchName(@PathVariable("title")String title){
        Map<String,Object> map = new HashMap<>();
        List<Task> list = null;
        list = taskService.getTaskByTitle(title);
        map.put("data",list);
        return map;
    }
    @GetMapping("/searchCreatorId/{creatorId}")
    @ResponseBody
    public Map searchCreatorId(@PathVariable("creatorId")Integer creatorId){
        Map<String,Object> map = new HashMap<>();
        List<Task> list = null;
        list = taskService.getTaskByCreatorId(creatorId);
        map.put("data",list);
        return map;
    }
    @GetMapping("/delete/{taskId}")
    public String delete(@PathVariable("taskId")Integer taskId, Model model){
        if(taskService.deleteTaskByTaskId(taskId)==1){
            model.addAttribute("message","删除成功！");
        }else{
            model.addAttribute("message","删除失败！");
        }
        return getTaskInfoManage();
    }

}
