package com.reed.info_manager.controller;

import com.reed.info_manager.entity.TaskReply;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.service.TaskReplyService;
import com.reed.info_manager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import static com.reed.info_manager.constant.Constant.FILE_PATH_IS_NULL;
import static com.reed.info_manager.constant.Constant.FILE_ROOT_DIR;

@Controller
@RequestMapping("/task/reply")
public class TaskReplyController {
    @Autowired
    HttpSession session;
    @Autowired
    TaskReplyService taskReplyService;

    @PostMapping
    @ResponseBody
    public void myReply(@RequestParam("file") MultipartFile file , TaskReply taskReply, Model model){
        User user = (User)session.getAttribute("user");
        if (!file.isEmpty()) {
            taskReply.setTaskReplyFilePath(parseFilePath(taskReply.getTaskReplyFilePath(),user.getName(),file.getOriginalFilename()));

            if (!FileUtils.saveFileFullPath(file, taskReply.getTaskReplyFilePath())) {
                model.addAttribute("message", "文件保存失败！");
            }
        }else taskReply.setTaskReplyFilePath(FILE_PATH_IS_NULL);
        taskReply.setTaskReplyTime(new Date());
        taskReply.setTaskResponderId(user.getId());

        if(taskReplyService.addReply(taskReply)==1){
            model.addAttribute("message","回复成功！");
        }else{
            model.addAttribute("message","回复失败！");
        }

    }

    public static String parseFilePath(String path,String userName,String fileName){
        File file = new File(path);
        return file.getParent()+"/"+userName+"-"+fileName;
    }
}
