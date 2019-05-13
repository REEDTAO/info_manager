package com.reed.info_manager.controller;

import com.reed.info_manager.entity.Task;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.service.TaskService;
import com.reed.info_manager.service.UserGroupService;
import com.reed.info_manager.service.UserService;
import com.reed.info_manager.utils.FileUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

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

    @GetMapping("/complete/{taskId}")
    public String completeTask(@PathVariable("taskId")Integer taskId, Model model){
        if(taskService.finishTask(taskId)==1){
            model.addAttribute("message","任务直接完成！");
        }else model.addAttribute("message","操作失败，请联系管理员！");
        return "redirect:/task/myCreateTaskList";
    }


    @PostMapping("/update")
    public String update(@RequestParam("file") MultipartFile file, Task task, Model model){
        FileUtils.deleteFileByFullPath(FILE_ROOT_DIR+task.getOldPath());

        handleData(session,task,file,model);

        System.out.println(task);

        if(taskService.updateTaskByTaskId(task)==1){
            model.addAttribute("message","修改成功！");
        }else model.addAttribute("message","修改失败，请联系管理员！");
        return myCreateTaskList();
    }

    public static void handleData(HttpSession session,Task task,MultipartFile file,Model model){
        User user = (User) session.getAttribute("user");
        Calendar calendar = Calendar.getInstance();
        task.setTaskCreateTime(calendar.getTime());
        String filePath = calendar.get(Calendar.YEAR)
                + "/" + (calendar.get(Calendar.MONTH) + 1)
                + "/" + (calendar.get(Calendar.DATE))
                + "/" + task.getTaskTitle()+"/";
        if (!file.isEmpty()){
            if (!FileUtils.saveFile(file,FILE_ROOT_DIR+filePath)){
                model.addAttribute("message","文件保存失败！");
                //return  taskIndex(model);
            }
            task.setTaskFilePath(filePath + file.getOriginalFilename());
        }else task.setTaskFilePath(filePath);

        task.setTaskCreatorId(user.getId());
        task.setTaskSubmittedNum(0);
        List<Integer> idList = new ArrayList<>();
        HashMap idMap = (HashMap) session.getAttribute("idMap");
        for (String name : task.getTaskTargetGroupList()){
            idList.add((Integer) idMap.get(name));
        }
        task.setTaskTargetGroupIds(idList);
    }

    @PostMapping("/create")
    public String taskCreat(@RequestParam("file") MultipartFile file, Task task, Model model){

        handleData(session,task,file,model);

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
    @GetMapping("/updateTask/{taskId}")
    public String getUpdateTaskPage(@PathVariable("taskId")Integer taskId,Model model){
        Task task = taskService.getTaskByTaskId(taskId);
        model.addAttribute("task",task);
        taskIndex(model);
        return "task/taskUpdate";
    }



}
