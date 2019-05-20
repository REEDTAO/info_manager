package com.reed.info_manager;


import com.github.pagehelper.PageHelper;
import com.reed.info_manager.constant.Constant;
import com.reed.info_manager.controller.InfoAnalysisController;
import com.reed.info_manager.entity.Task;
import com.reed.info_manager.entity.Track;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.mapper.UserGroupMapper;
import com.reed.info_manager.mapper.UserRoleMapper;
import com.reed.info_manager.service.*;
import com.reed.info_manager.utils.ExcelUtils;
import com.reed.info_manager.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class InfoManagerApplicationTests {

    @Autowired
    UserService service;
    @Autowired
    UserGroupMapper mapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    TaskService taskService;
    @Autowired
    InfoAnalysisService infoAnalysisService;
    @Autowired
    TaskReplyService taskReplyService;
    @Autowired
    SpringTemplateEngine engine;

    @Test
    public void test(){
        List<User> list = new ArrayList<>();
        for(int i=2;i<=10;i++){
            User user=new User();
            user.setName("卢"+i);
            user.setAddr("广东");
            user.setEmail("qq.com");
            user.setPassword("5252");
            user.setSex("男");
            user.setTel("110"+i);
            list.add(user);

        }
        service.addUsers(list);
    }


    @Test
    public void testPageHelper(){
        PageHelper.startPage(4,10);

        System.out.println(mapper.selectAllGroupByGroupName("美术"));
    }

    @Test
    public void testUserRoleMapper(){
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(21);
//        list.add(22);
//        Integer num = userRoleMapper.selectUserNumByGroupName(list);
//        System.out.println(num);
        System.out.println(userRoleMapper.test("21,22"));
    }

    @Test
    public void testTaskMapper(){
        List<Task> list = taskService.getMyReceiveTaskListUnfinished(3);

        System.out.println(list);
    }

    @Test
    public void testFilePack(){
        FileUtils.packFile("2019/5/9/admin第一次提交");
    }

    @Test
    public void testInfoAnalysis(){
//        List<Integer> lineChartData = new ArrayList<>();
//        for(int i= 1;i<=7;i++){
//            lineChartData.add(infoAnalysisService.getTaskNumInWeek(InfoAnalysisController.getTimeForWeek(i)));
//        }
//        System.out.println(lineChartData);
    }
    @Test
    public void testHtmlEngine() throws IOException {
        List<User> list = ExcelUtils.parseFileToUserList("/Users/luweitao/Desktop/testExcel.xlsx");
        System.out.println(list);


    }
}
