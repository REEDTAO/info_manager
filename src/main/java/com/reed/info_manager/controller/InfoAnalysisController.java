package com.reed.info_manager.controller;

import com.reed.info_manager.constant.Constant;
import com.reed.info_manager.entity.*;
import com.reed.info_manager.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.thymeleaf.spring5.SpringTemplateEngine;


import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;
import java.util.List;



@Controller
@RequestMapping("/infoAnalysis")
public class InfoAnalysisController {

    @Autowired
    InfoAnalysisService infoAnalysisService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    HttpSession session;
    @Autowired
    TaskReplyService taskReplyService;
    @Autowired
    UserService userService;


    @GetMapping
    public String getInfoAnalysis(Model model){
        //lineChart initData
        User user = (User) session.getAttribute("user");
//        List<Integer> lineChartData = new ArrayList<>();
//        for(int i= 1;i<=7;i++){
//            lineChartData.add(infoAnalysisService.getTaskNumInWeek(getTimeForWeek(i,user.getId())));
//        }
//        model.addAttribute("lineChartData",lineChartData);
//
//        System.out.println(lineChartData);
//
//        List<UserGroup> allGroup = userGroupService.getAllGroup(user.getId());
//        session.setAttribute("userGroupList",allGroup);
        List<UserGroup> allGroup = userRoleService.searchMyJoinUserGroup(user.getId());
        if (allGroup!=null&&allGroup.size()!=0) {
            List<String> groupNameList  = new ArrayList<>();
            Map<String,Integer> idMaps = new HashMap<>();
            for (UserGroup group : allGroup) {
                groupNameList.add(group.getName());
                idMaps.put(group.getName(),group.getUserGroupId());
            }
            session.setAttribute("idMap",idMaps);
            session.setAttribute("targetList",groupNameList);
            model.addAttribute("targetList",groupNameList);
        }
        return "info/infoAnalysisIndex";
    }

    @PostMapping("/getMyTrack")
    public String getMyTrack(String userGroupName,Model model){
        User user = (User) session.getAttribute("user");
        Map<String,Integer> idMaps = (Map<String, Integer>) session.getAttribute("idMap");
        Integer groupId = idMaps.get(userGroupName);
        List<Track> list = taskReplyService.getTaskReplyByTaskGroupNameAndUserId(groupId,user.getId());
        model.addAttribute("list",list);
        model.addAttribute("userGroupId",groupId);
        model.addAttribute("targetList",session.getAttribute("targetList"));
        return "info/infoAnalysisIndex";
    }



    @GetMapping("/getPieChartData")
    @ResponseBody
    public Object[] getPieChartData(){
        User user = (User) session.getAttribute("user");
        List<PieChartData> list= taskReplyService.getTaskReplyListByUserId(user.getId());
        return list.toArray();
    }

    @GetMapping("/getLineChartData")
    @ResponseBody
    public Map getLineChartData(){
        int[][] line1 = {{1,1},{2,2},{3,3}};
        Map map = new HashMap();
        map.put("data",line1);
        map.put("color", Constant.randomColor());
        map.put("label","line1");
        return map;
    }
    @GetMapping("/getLineChartData/{taskGroupName}")
    @ResponseBody
    public Map getLineChartDataByTaskGroupId(@PathVariable("taskGroupName") String taskGroupName){

        User user = (User) session.getAttribute("user");
        Map<String,Integer> idMaps = (Map<String, Integer>) session.getAttribute("idMap");
        List<Integer> list = new ArrayList<>();

        //System.out.println(idMaps.get(taskGroupName));
        int[][] data = new int[7][2];
        for(int i =1;i<=7;i++){
            Map map = getTimeForWeek(i, user.getId());
            map.put("taskGroupId",idMaps.get(taskGroupName));
            data[i-1][0]=i;
            data[i-1][1]=infoAnalysisService.getTaskNumByGroup(map);
        }

        Map map = new HashMap();
        map.put("data",data);
        map.put("color", Constant.randomColor());
        map.put("label",taskGroupName);
        System.out.println(map);
        return map;
    }


    public static Map getTimeForWeek(int weekNum,Integer userId){
        Map<String, Object> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE)-calendar.get(Calendar.DAY_OF_WEEK)-(weekNum-1)*7);
        //System.out.println(calendar.getTime());
        map.put("endTime",calendar.getTime());
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE)-calendar.get(Calendar.DAY_OF_WEEK)-weekNum*7+1);
//        System.out.println(calendar.getTime());
        map.put("beginTime",calendar.getTime());
        map.put("userId",userId);

        return map;
    }

    @GetMapping("/bindStudentDetail/{userId}")
    public String bindStudentDetail(@PathVariable("userId")Integer userId,Model model){
        User user = userService.getUserDetailByUserId(userId);
        session.setAttribute("user",user);
        getInfoAnalysis(model);
        return "parent/studentInfoAnalysisIndex";
    }

    @PostMapping("/parent/getMyTrack")
    public String parentGetMyTrack(String userGroupName,Model model){
        getMyTrack(userGroupName,model);
        return "parent/studentInfoAnalysisIndex";
    }



}
