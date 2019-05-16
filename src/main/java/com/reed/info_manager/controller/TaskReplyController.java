package com.reed.info_manager.controller;

import com.reed.info_manager.entity.TaskReply;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.service.TaskReplyService;
import com.reed.info_manager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;

import static com.reed.info_manager.constant.Constant.FILE_ROOT_DIR;

@Controller
@RequestMapping("/task/reply")
public class TaskReplyController {
    @Autowired
    HttpSession session;
    @Autowired
    TaskReplyService taskReplyService;

    @PostMapping
    public String  myReply(@RequestParam("file") MultipartFile file , TaskReply taskReply, Model model){
        User user = (User)session.getAttribute("user");
        taskReply.setTaskReplyFilePath(taskReply.getTaskReplyFilePath().substring(0,taskReply.getTaskReplyFilePath().lastIndexOf("/")));
        handleUpload(file,taskReply,model,user);


        if(taskReplyService.addReply(taskReply)==1){
            model.addAttribute("message","回复成功！");
        }else{
            model.addAttribute("message","回复失败！");
        }
        model.addAttribute("taskReply",taskReply);
        return "task/myReceiveFinishedDetail";
    }
    @PostMapping("/update")
    public String myReplyUpdate(@RequestParam("file") MultipartFile file , TaskReply taskReply, Model model){
        User user = (User)session.getAttribute("user");
        handleUpload(file,taskReply,model,user);
        if(taskReplyService.updateTaskReply(taskReply)==1){
            model.addAttribute("message","修改成功！");
        }else{
            model.addAttribute("message","修改失败！请联系管理员！");
        }
        taskReply.setTaskReplyFilePath(taskReply.getTaskReplyFilePath().substring(0,taskReply.getTaskReplyFilePath().lastIndexOf("/")));
        model.addAttribute("taskReply",taskReply);
        return "task/myReceiveFinishedDetail";
    }


    @GetMapping("/finished/{taskId}")
    public String getMyFinishedReply(@PathVariable("taskId") String taskId,Model model){
        User user = (User)session.getAttribute("user");
        TaskReply reply = taskReplyService.getTaskReplyByTaskId(taskId,user.getId());
        String taskReplyFilePath = reply.getTaskReplyFilePath();
        reply.setTaskReplyFilePath(taskReplyFilePath.substring(0,taskReplyFilePath.lastIndexOf("/")));
        model.addAttribute("taskReply",reply);
        return "task/myReceiveFinishedDetail";
    }





    public static void handleUpload(MultipartFile file , TaskReply taskReply, Model model,User user){
        if (!file.isEmpty()) {
            taskReply.setTaskReplyFilePath(FILE_ROOT_DIR+taskReply.getTaskReplyFilePath()+"/"+user.getName()+"-"+file.getOriginalFilename());
            System.out.println(taskReply.getTaskReplyFilePath());
            if (!FileUtils.saveFileFullPath(file, taskReply.getTaskReplyFilePath(),user.getName())) {
                model.addAttribute("message", "文件保存失败！");
            }
            taskReply.setTaskReplyFilePath(taskReply.getTaskReplyFilePath().substring(FILE_ROOT_DIR.length()));
        }else taskReply.setTaskReplyFilePath(taskReply.getTaskReplyFilePath()+"/");
        taskReply.setTaskReplyTime(new Date());
        taskReply.setTaskResponderId(user.getId());
        System.out.println(taskReply.getTaskReplyFilePath());
    }
}
