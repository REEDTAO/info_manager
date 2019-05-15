package com.reed.info_manager;


import com.github.pagehelper.PageHelper;
import com.reed.info_manager.controller.InfoAnalysisController;
import com.reed.info_manager.entity.Task;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.mapper.UserGroupMapper;
import com.reed.info_manager.mapper.UserRoleMapper;
import com.reed.info_manager.service.InfoAnalysisService;
import com.reed.info_manager.service.TaskService;
import com.reed.info_manager.service.UserGroupService;
import com.reed.info_manager.service.UserService;
import com.reed.info_manager.utils.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
