package com.reed.info_manager.controller;

import com.reed.info_manager.entity.Task;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.mapper.UserMapper;
import com.reed.info_manager.service.TaskService;
import com.reed.info_manager.service.UserGroupService;
import com.reed.info_manager.service.UserService;
import com.reed.info_manager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

import static com.reed.info_manager.constant.Constant.FILE_PATH_IS_NULL;
import static com.reed.info_manager.constant.Constant.FILE_ROOT_DIR;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    HttpSession session;
    @Autowired
    TaskService taskService;
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    UserService userService;

    @GetMapping("/create")
    public String taskIndex(Model model){
        User user = (User) session.getAttribute("user");

        List<UserGroup> allGroup = userGroupService.getAllGroup(user.getId());
        if (allGroup!=null&&allGroup.size()!=0) {
            List<String> groupNameList  = new ArrayList<>();
            Map<String,Integer> idMaps = new HashMap<>();
            for (UserGroup group : allGroup) {
                groupNameList.add(group.getName());
                idMaps.put(group.getName(),group.getUserGroupId());
            }
            session.setAttribute("idMap",idMaps);
            model.addAttribute("targetList",groupNameList);
        }
        return "task/taskCreate";
    }



    @PostMapping("/create")
    public String taskCreat(@RequestParam("file") MultipartFile file, Task task, Model model){

        User user = (User) session.getAttribute("user");
        Calendar calendar = Calendar.getInstance();
        task.setTaskCreateTime(calendar.getTime());
        String filePath="";

        if (!file.isEmpty()){
            filePath = FILE_ROOT_DIR
                    + calendar.get(Calendar.YEAR)
                    + "/" + (calendar.get(Calendar.MONTH) + 1)
                    + "/" + (calendar.get(Calendar.DATE))
                    + "/" + task.getTaskTitle();
            task.setTaskFilePath(filePath + "/" + file.getOriginalFilename());
            if (!FileUtils.saveFile(file,filePath)){
                model.addAttribute("message","文件保存失败！");
                return  taskIndex(model);
            }
        }else task.setTaskFilePath(FILE_PATH_IS_NULL);

        task.setTaskCreatorId(user.getId());
        task.setTaskSubmittedNum(0);
        List<Integer> idList = new ArrayList<>();
        HashMap idMap = (HashMap) session.getAttribute("idMap");
        for (String name : task.getTaskTargetGroupList()){
            idList.add((Integer) idMap.get(name));
        }
        task.setTaskTargetGroupIds(idList);

        if (taskService.addTask(task)>=1){
            model.addAttribute("message","发布成功！");
        }else{
            model.addAttribute("message","发布失败，请联系管理员处理！");
        }
        return taskIndex(model);

    }
    @GetMapping("/myCreateTaskList")
    public String myCreateTaskList(){
        return "task/myCreateTaskList";
    }

    @GetMapping("/myCreateTaskList/unfinished")
    @ResponseBody
    public Map getMyCreateUnfinished(){
        User user = (User) session.getAttribute("user");
        List<Task> unfinishedList = taskService.getAllUnfinishedTaskByUserId(user.getId());
        Map<String,List> map = new HashMap();
        map.put("data",unfinishedList);
        return map;
    }

    @GetMapping("/myCreateTaskList/finished")
    @ResponseBody
    public Map getMyCreateFinished(){
        User user = (User) session.getAttribute("user");
        List<Task> finishedList = taskService.getAllFinishedTaskByUserId(user.getId());
        Map<String,List> map = new HashMap();
        map.put("data",finishedList);
        return map;
    }


    @GetMapping("/myCreateTaskList/taskDetail/{taskId}")
    public String getTaskDetail(@PathVariable("taskId") Integer taskId){
        return "task/taskDetail";
    }

    @GetMapping("/myReceiveTaskList")
    public String getMyReceive(){
        return "task/myReceive";
    }

    @GetMapping("/myReceiveTaskList/unfinished")
    @ResponseBody
    public Map getMyReceiveTaskListUnfinished(){
        User user = (User) session.getAttribute("user");
        List<Task> list = taskService.getMyReceiveTaskListUnfinished(user.getId());
        Map<String,List> map = new HashMap();
        map.put("data",list);
        return map;
    }
    @GetMapping("/myReceiveTaskList/unfinish/taskDetail/{taskId}")
    public String getMyReceiveTaskUnfinishedTaskDetail(@PathVariable("taskId") Integer taskId,Model model){
        User user  = (User) session.getAttribute("user");
        Task task = taskService.getTaskByTaskId(taskId);
        String creatorName = userService.getUserNameByUserId(task.getTaskCreatorId());
        model.addAttribute("creatorName",creatorName);
        model.addAttribute("task",task);
        return  "task/myReceiveUnifinishedDetail";
    }

    @GetMapping("/myReceiveTaskList/finished")
    @ResponseBody
    public Map getMyReceiveTaskListFinished(){
        User user = (User) session.getAttribute("user");
        List<Task> list = taskService.getMyReceiveTaskListFinished(user.getId());
        Map<String,List> map = new HashMap();
        map.put("data",list);
        return map;
    }

}
