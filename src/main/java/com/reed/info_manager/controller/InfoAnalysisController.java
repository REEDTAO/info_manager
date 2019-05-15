package com.reed.info_manager.controller;

import com.reed.info_manager.entity.PieChartData;
import com.reed.info_manager.entity.TaskReply;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.entity.UserGroup;
import com.reed.info_manager.service.InfoAnalysisService;
import com.reed.info_manager.service.TaskReplyService;
import com.reed.info_manager.service.UserGroupService;
import com.reed.info_manager.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/infoAnalysis")
public class InfoAnalysisController {

    @Autowired
    InfoAnalysisService infoAnalysisService;
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    HttpSession session;
    @Autowired
    TaskReplyService taskReplyService;

    @GetMapping
    public String getInfoAnalysis(Model model){
        //lineChart initData
        User user = (User) session.getAttribute("user");
        List<Integer> lineChartData = new ArrayList<>();
        for(int i= 1;i<=7;i++){
            lineChartData.add(infoAnalysisService.getTaskNumInWeek(getTimeForWeek(i,user.getId())));
        }
        model.addAttribute("lineChartData",lineChartData);

        System.out.println(lineChartData);

        List<UserGroup> allGroup = userGroupService.getAllGroup(user.getId());
        session.setAttribute("userGroupList",allGroup);


        return "info/infoAnalysisIndex";
    }

    @GetMapping("/getPieChartData")
    @ResponseBody
    public PieChartData[] getPieChartData(){
        User user = (User) session.getAttribute("user");
        List<PieChartData> list= taskReplyService.getTaskReplyListByUserId(user.getId());
        return (PieChartData[]) list.toArray();
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

    public static String randomColor() {
        Random random = new Random();
        int r = random.nextInt(256);

        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return "#"+Integer.toHexString(r)+Integer.toHexString(g)+Integer.toHexString(b);
    }

}
