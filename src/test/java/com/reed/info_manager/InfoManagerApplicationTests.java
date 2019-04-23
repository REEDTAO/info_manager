package com.reed.info_manager;


import com.github.pagehelper.PageHelper;
import com.reed.info_manager.entity.User;
import com.reed.info_manager.mapper.UserGroupMapper;
import com.reed.info_manager.service.UserGroupService;
import com.reed.info_manager.service.UserService;
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

}
